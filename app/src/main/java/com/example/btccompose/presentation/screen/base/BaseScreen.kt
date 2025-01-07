package com.example.btccompose.presentation.screen.base

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.btccompose.domain.model.ErrorState
import com.example.btccompose.presentation.screen.dialogs.ErrorDialog
import com.example.btccompose.presentation.screen.dialogs.LoadingDialog
import com.example.btccompose.presentation.screen.dialogs.NetworkErrorDialog
import com.example.btccompose.presentation.viewmodel.base.BaseViewModel

@Composable
fun <T : BaseViewModel> BaseScreen(
    viewModel: T,
    onRetry: (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    val error by viewModel.error.collectAsStateWithLifecycle()
    val complete by viewModel.complete.collectAsStateWithLifecycle()
    
    var visible by remember { mutableStateOf(false) }
    
    LaunchedEffect(Unit) {
        visible = true
    }

    Box(modifier = Modifier.fillMaxSize()) {
        AnimatedVisibility(
            visible = visible,
            enter = fadeIn(tween(500)) + slideInVertically(
                initialOffsetY = { it * 2 },
                animationSpec = tween(500)
            )
        ) {
            content()
        }

        if (isLoading) {
            LoadingDialog()
        }

        error?.let { errorState ->
            when (errorState) {
                is ErrorState.NetworkError -> {
                    NetworkErrorDialog(
                        message = errorState.message,
                        onDismiss = viewModel::clearError,
                        onRetry = {
                            viewModel.clearError()
                            onRetry?.invoke()
                        }
                    )
                }
                is ErrorState.GeneralError -> {
                    ErrorDialog(
                        exception = errorState.exception,
                        onDismiss = viewModel::clearError
                    )
                }
            }
        }
    }
}