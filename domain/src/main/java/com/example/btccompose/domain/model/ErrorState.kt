package com.example.btccompose.domain.model

sealed class ErrorState {
    data class NetworkError(val message: String) : ErrorState()
    data class GeneralError(val exception: Exception) : ErrorState()
} 