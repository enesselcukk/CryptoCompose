package com.example.btccompose.utils

sealed class NetworkState<out T> {
    data class Success<out T>(val data: T) : NetworkState<T>()
    data class Error<T>(val message: Exception?) : NetworkState<T>()
    data object Loading : NetworkState<Nothing>()
    data object Completed : NetworkState<Nothing>()
}
