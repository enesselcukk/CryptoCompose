package com.example.btccompose.domain.repository

import com.example.btccompose.domain.model.BtcResponseMapper
import com.example.btccompose.utils.NetworkState
import kotlinx.coroutines.flow.Flow

interface BtcRepositoryLocal {
    suspend fun getLocalBtc(): Flow<NetworkState<List<BtcResponseMapper>>>
    suspend fun insertBtc(btcResponseMapper: BtcResponseMapper): Flow<NetworkState<Unit>>
    suspend fun getById(id: Int): Flow<NetworkState<BtcResponseMapper?>>
    suspend fun getFavoritesByIds(ids: List<Int>): Flow<NetworkState<List<BtcResponseMapper>>>
    suspend fun deleteAll(): Flow<NetworkState<Unit>>
    suspend fun deleteById(id: Int): Flow<NetworkState<Unit>>
}