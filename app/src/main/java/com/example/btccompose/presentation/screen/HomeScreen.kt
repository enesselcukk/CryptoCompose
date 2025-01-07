package com.example.btccompose.presentation.screen


import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.btccompose.R
import com.example.btccompose.presentation.viewmodel.BtcViewModel
import com.example.btccompose.navigation.DetailScreenModel
import com.example.btccompose.domain.model.BtcResponseMapper
import com.example.btccompose.presentation.screen.base.BaseScreen
import com.example.btccompose.presentation.component.MiniChart
import com.example.btccompose.presentation.theme.NegativeRed
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun HomeScreen(
    viewModel: BtcViewModel = hiltViewModel(),
    goDetailClick: (DetailScreenModel) -> Unit,
) {
    BaseScreen(
        viewModel = viewModel,
        onRetry = {
            viewModel.getBtcList()
        }
    ) {
        val getLocalBtc by viewModel.getLocalBtc.collectAsStateWithLifecycle()
        val getBtc by viewModel.filteredBtc.collectAsStateWithLifecycle()
        val favoriteIds by viewModel.favoriteIds.collectAsStateWithLifecycle()
        val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()
        val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()

        LaunchedEffect(Unit) {
            viewModel.resetSearch()
            viewModel.getBtcList()
            viewModel.getLocal()
        }

        LaunchedEffect(getLocalBtc) {
            getLocalBtc?.let { localData ->
                viewModel.loadFavoritesFromDb(localData.map { it.id })
            }
        }

        val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isLoading)
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = {
                viewModel.clearError()
                viewModel.getBtcList()
                viewModel.getLocal()
            }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                ) {
                    FavoritesSection()
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(R.string.titleBtc),
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurface,
                            fontSize = 20.sp
                        )
                        AnimatedSearchBar(
                            query = searchQuery,
                            onQueryChange = viewModel::onSearchQueryChange
                        )
                    }

                    BtcItems(
                        getBtc,
                        goDetailClick = goDetailClick,
                        likedClick = viewModel::toggleFavorite,
                        favoriteIds = favoriteIds,
                        onLoadMore = {
                            if (isLoading.not()) {
                                viewModel.loadMoreData()
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun BtcItems(
    data: List<BtcResponseMapper>,
    goDetailClick: (DetailScreenModel) -> Unit,
    likedClick: (BtcResponseMapper) -> Unit,
    favoriteIds: Set<Int>,
    onLoadMore: () -> Unit,
    viewModel: BtcViewModel = hiltViewModel()
) {
    val isLoadingMore by viewModel.isLoadingMore.collectAsState()

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(5.dp),
        contentPadding = PaddingValues(top = 5.dp, start = 5.dp, end = 5.dp),
    ) {
        items(
            items = data,
            key = { it.id }
        ) { item ->
            Btc(
                btcResponseMapper = item,
                goDetailClick = goDetailClick,
                likedClick = likedClick,
                isFavorite = favoriteIds.contains(item.id)
            )

            if (item == data.lastOrNull()) {
                LaunchedEffect(Unit) {
                    onLoadMore()
                }
            }
        }

        item {
            if (isLoadingMore) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}

@Composable
fun AnimatedSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
) {
    var isExpanded by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val width by animateFloatAsState(
        targetValue = if (isExpanded) 280f else 48f,
        animationSpec = tween(
            durationMillis = 300,
            easing = FastOutSlowInEasing
        ),
        label = ""
    )

    LaunchedEffect(isExpanded) {
        if (isExpanded) {
            keyboardController?.show()
        } else {
            keyboardController?.hide()
        }
    }

    Row(
        modifier = Modifier
            .height(48.dp)
            .width(width.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (width > 50f) {
            TextField(
                value = query,
                onValueChange = onQueryChange,
                modifier = Modifier
                    .width(width.dp)
                    .height(48.dp)
                    .padding(end = 10.dp)
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f),
                        shape = MaterialTheme.shapes.medium
                    ),
                placeholder = {
                    Text(
                        stringResource(R.string.searchCrypto),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                },
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f),
                    unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f),
                    disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    focusedTextColor = MaterialTheme.colorScheme.onSurface,
                    unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                ),
                shape = MaterialTheme.shapes.medium,
                textStyle = MaterialTheme.typography.bodyMedium,
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.clickable { isExpanded = false }
                    )
                }
            )
        } else {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "",
                tint = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .size(48.dp)
                    .padding(12.dp)
                    .clickable { isExpanded = true }
            )
        }
    }
}

@Composable
fun Btc(
    btcResponseMapper: BtcResponseMapper,
    goDetailClick: (DetailScreenModel) -> Unit,
    likedClick: (BtcResponseMapper) -> Unit,
    isFavorite: Boolean,
) {
    ConstraintLayout(modifier = Modifier
        .clickable {
            goDetailClick(
                DetailScreenModel(
                    symbol = btcResponseMapper.pair.toString(),
                    denominatorSymbol = btcResponseMapper.denominatorSymbol.toString(),
                    numeratorSymbol = btcResponseMapper.numeratorSymbol.toString(),
                )
            )
        }
        .fillMaxSize()
        .padding(horizontal = 16.dp, vertical = 12.dp)) {
        val (icon, btcName, btcLast, miniChart, btcDailyPercent, numeratorSymbolVolume, line) = createRefs()

        IconButton(onClick = {
            likedClick(btcResponseMapper)
        }, modifier = Modifier
            .size(20.dp)
            .constrainAs(icon) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                bottom.linkTo(parent.bottom)
            }) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = "",
                tint = if (isFavorite) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant
            )
        }

        Text(
            text = "${btcResponseMapper.numeratorSymbol}/${btcResponseMapper.denominatorSymbol}",
            modifier = Modifier.constrainAs(btcName) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(icon.end, margin = 8.dp)
                end.linkTo(miniChart.start, margin = 8.dp)
            },
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )

        MiniChart(
            modifier = Modifier.constrainAs(miniChart) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(btcName.end, margin = 50.dp)
            }
        )

        Text(
            text = btcResponseMapper.formattedLast.toString(),
            modifier = Modifier.constrainAs(btcLast) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                end.linkTo(btcDailyPercent.start, margin = 8.dp)
            },
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )

        Box(
            modifier = Modifier
                .background(
                    color = NegativeRed,
                    shape = RoundedCornerShape(4.dp)
                )
                .padding(horizontal = 6.dp, vertical = 3.dp)
                .constrainAs(btcDailyPercent) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                }
        ) {
            Text(
                text = stringResource(R.string.dailyPercent, btcResponseMapper.newPercent.orEmpty()),
                style = MaterialTheme.typography.bodySmall,
                color = Color.White
            )
        }

        Text(
            text = "${btcResponseMapper.volume?.toDouble()?.toInt()} ${btcResponseMapper.numeratorSymbol}",
            modifier = Modifier.constrainAs(numeratorSymbolVolume) {
                top.linkTo(btcDailyPercent.bottom, margin = 4.dp)
                end.linkTo(parent.end)
                bottom.linkTo(line.top)
            },
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(0.5.dp)
                .background(color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
                .constrainAs(line) {
                    top.linkTo(numeratorSymbolVolume.bottom, margin = 4.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
        )
    }
}

