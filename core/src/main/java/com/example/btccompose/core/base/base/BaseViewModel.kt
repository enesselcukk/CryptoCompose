package com.example.btccompose.presentation.viewmodel.base

import androidx.lifecycle.ViewModel
import com.example.btccompose.data.interceptor.NoInternetException
import com.example.btccompose.domain.model.ErrorState
import com.example.btccompose.utils.NetworkState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

open class BaseViewModel @Inject constructor() : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<ErrorState?>(null)
    val error = _error.asStateFlow()

    private val _complete = MutableStateFlow(false)
    val complete = _complete.asStateFlow()

    suspend fun <T : Any?> handleNetworkState(
        flow: Flow<NetworkState<T>>,
        onSuccess: (T) -> Unit,
        onError: ((Exception) -> Unit)? = null,
    ) {
        flow.collect {
            when (it) {
                is NetworkState.Success -> {
                    _isLoading.value = false
                    onSuccess(it.data)
                }

                is NetworkState.Error -> {
                    _isLoading.value = false
                    when (it.message) {
                        is NoInternetException -> _error.value = ErrorState.NetworkError(it.message.message.toString())
                        else -> it.message?.let { exception -> 
                            _error.value = ErrorState.GeneralError(exception)
                            onError?.invoke(exception)
                        }
                    }
                }

                is NetworkState.Loading -> {
                    _isLoading.value = true
                }

                is NetworkState.Completed -> {
                    _isLoading.value = false
                    _complete.value = true
                }
            }
        }
    }

    fun clearError() {
        _error.value = null
    }
}