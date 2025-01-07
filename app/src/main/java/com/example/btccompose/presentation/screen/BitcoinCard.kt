package com.example.btccompose.presentation.screen

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.btccompose.R
import com.example.btccompose.presentation.theme.PositiveGreen
import com.example.btccompose.presentation.theme.NegativeRed
import com.example.btccompose.presentation.viewmodel.BtcViewModel
import androidx.compose.material3.TextButton
import androidx.compose.foundation.clickable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.ui.text.style.TextAlign
import kotlinx.coroutines.delay
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.IntOffset
import kotlin.math.roundToInt


@Composable
fun FavoritesSection(
    viewModel: BtcViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.getLocal()
    }

    val btcData by viewModel.getLocalBtc.collectAsStateWithLifecycle()
    val isExpanded = btcData.isNullOrEmpty().not()
    var animateVisibility by remember { mutableStateOf(false) }
    var selectedItemId by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(btcData) {
        animateVisibility = isExpanded
    }

    AnimatedVisibility(
        visible = animateVisibility,
        enter = expandVertically(animationSpec = tween(durationMillis = 300)),
        exit = shrinkVertically(animationSpec = tween(durationMillis = 200))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.titleBtcFavorite),
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                TextButton(
                    onClick = { 
                        viewModel.clearAllFavorites()
                        selectedItemId = null 
                    },
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = Color(0xFFFF4646)
                    )
                ) {
                    Text(
                        text = stringResource(R.string.clear),
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }
            LazyRow(contentPadding = PaddingValues(horizontal = 8.dp)) {
                btcData?.let { items ->
                    items(
                        items = items.sortedBy { it.pair },
                        key = { it.id }
                    ) { bitcoin ->
                        BitcoinCard(
                            pair = bitcoin.pair.orEmpty(),
                            last = bitcoin.formattedLast.toString(),
                            newPercent = bitcoin.newPercent.toString(),
                            dailyPercent = bitcoin.dailyPercent.toString(),
                            onDelete = { 
                                viewModel.deleteFavorite(bitcoin.id)
                                if (selectedItemId == bitcoin.id.toString()) {
                                    selectedItemId = null
                                }
                            },
                            isAnyItemSelected = selectedItemId != null,
                            isThisItemSelected = selectedItemId == bitcoin.id.toString(),
                            onItemSelected = { isSelected ->
                                selectedItemId = if (isSelected) bitcoin.id.toString() else null
                            }
                        )
                    }
                }
            }
        }
    }
}

@SuppressLint("UseOfNonLambdaOffsetOverload")
@Composable
private fun BitcoinCard(
    pair: String,
    last: String,
    dailyPercent: String,
    newPercent:String,
    onDelete: () -> Unit,
    isAnyItemSelected: Boolean,
    isThisItemSelected: Boolean,
    onItemSelected: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val density = LocalDensity.current
    var isVisible by remember { mutableStateOf(false) }
    
    val appearScale by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0f,
        animationSpec = tween(
            durationMillis = 500,
            easing = FastOutSlowInEasing
        ),
        label = ""
    )

    LaunchedEffect(Unit) {
        isVisible = true
    }

    val offsetX by animateFloatAsState(
        targetValue = if (isThisItemSelected) 5f else 0f,
        animationSpec = if (isThisItemSelected) {
            infiniteRepeatable(
                animation = tween(80),
                repeatMode = RepeatMode.Reverse
            )
        } else {
            tween(80)
        },
        label = ""
    )

    val scale by animateFloatAsState(
        targetValue = if (isThisItemSelected) 1.1f else 1f,
        animationSpec = tween(200),
        label = ""
    )

    val itemAlpha by animateFloatAsState(
        targetValue = when {
            isAnyItemSelected.not() -> 1f
            isThisItemSelected -> 1f
            else -> 0.5f
        },
        animationSpec = tween(200),
        label = ""
    )

    LaunchedEffect(isThisItemSelected) {
        if (isThisItemSelected) {
            delay(2000)
            onItemSelected(false)
        }
    }

    Box(modifier = Modifier
        .padding(horizontal = if (isThisItemSelected) 8.dp else 0.dp)
        .offset {
            IntOffset(
                x = (offsetX * density.density).roundToInt(),
                y = 0
            )
        }
        .scale(scale * appearScale)
        .graphicsLayer { 
           alpha = itemAlpha
        }
        .pointerInput(Unit) {
            detectTapGestures(
                onLongPress = {
                    onItemSelected(true)
                },
                onTap = {
                    onItemSelected(false)
                }
            )
        }) {
        Card(
            modifier = modifier
                .width(120.dp)
                .padding(3.dp),
            colors = CardDefaults.cardColors(),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 2.dp
            )
        ) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = pair,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(bottom = 4.dp),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = last,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center
                )
                Text(
                    text =  stringResource(R.string.dailyPercent, newPercent),
                    style = MaterialTheme.typography.bodySmall,
                    color = if (dailyPercent.startsWith("-")) NegativeRed else PositiveGreen,
                    modifier = Modifier.padding(top = 2.dp),
                    textAlign = TextAlign.Center
                )
            }
        }

        if (isThisItemSelected) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 4.dp, end = 4.dp)
                    .size(24.dp)
                    .graphicsLayer(alpha = 0.9f)
            ) {
                Card(
                    modifier = Modifier.fillMaxSize(),
                    shape = MaterialTheme.shapes.small,
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.9f)
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 4.dp
                    )
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable(onClick = onDelete),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.onErrorContainer,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }
        }
    }
}
