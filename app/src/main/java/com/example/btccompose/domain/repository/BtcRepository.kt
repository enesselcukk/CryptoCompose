package com.example.btccompose.domain.repository

import com.example.btccompose.data.model.BTCDataResponse
import com.example.btccompose.data.model.BtcDataResponseGraph
import com.example.btccompose.domain.model.BtcResponseMapper
import com.example.btccompose.utils.NetworkState
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Query

interface BtcRepository {
    suspend fun getBtc(): Flow<NetworkState<BTCDataResponse>>

    suspend fun getBtcDetail(
        from: Long,
        resolution: Int,
        symbol: String,
        to: Long
    ): Flow<NetworkState<BtcDataResponseGraph>>
}