package com.example.btccompose.data.repository

import com.example.btccompose.data.api.BtcApi
import com.example.btccompose.data.api.BtcGraphApi
import com.example.btccompose.data.model.BTCDataResponse
import com.example.btccompose.data.model.BtcDataResponseGraph
import com.example.btccompose.di.IoDispatcher
import com.example.btccompose.domain.model.BtcResponseMapper
import com.example.btccompose.domain.repository.BtcRepository
import com.example.btccompose.utils.NetworkState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class BtcRepositoryImpl @Inject constructor(
    private val btcApi: BtcApi,
    private val btcGraphApi: BtcGraphApi,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : BtcRepository {
    override suspend fun getBtc(): Flow<NetworkState<BTCDataResponse>> = flow {
        emit(NetworkState.Loading)
        try {
            val response = btcApi.getBtc()
            emit(NetworkState.Success(response))
        } catch (ex: Exception) {
            emit(NetworkState.Error(ex))
        } finally {
            emit(NetworkState.Completed)
        }
    }.flowOn(dispatcher)

    override suspend fun getBtcDetail(
        from: Long,
        resolution: Int,
        symbol: String,
        to: Long
    ): Flow<NetworkState<BtcDataResponseGraph>> = flow {
        emit(NetworkState.Loading)
        try {
            val response = btcGraphApi.getBtcDetail(from, resolution, symbol, to)
            emit(NetworkState.Success(response))
        } catch (ex: Exception) {
            emit(NetworkState.Error(ex))
        } finally {
            emit(NetworkState.Completed)
        }
    }.flowOn(dispatcher)
}