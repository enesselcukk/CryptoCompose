package com.example.btccompose.domain.base

import com.example.btccompose.utils.NetworkState
import kotlinx.coroutines.flow.Flow

interface BaseUseCase<in P, out R> {
    suspend operator fun invoke(params: P): Flow<NetworkState<R>>
}

interface BaseUseCaseNoParams<out R> {
    suspend operator fun invoke(): Flow<NetworkState<R>>
} 