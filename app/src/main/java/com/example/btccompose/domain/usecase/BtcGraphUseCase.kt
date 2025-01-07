package com.example.btccompose.domain.usecase

import com.example.btccompose.data.model.BtcDataResponseGraph
import com.example.btccompose.domain.base.BaseUseCase
import com.example.btccompose.domain.repository.BtcRepository
import com.example.btccompose.utils.NetworkState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BtcGraphUseCase @Inject constructor(
    private val btcRepository: BtcRepository
) : BaseUseCase<BtcGraphParams, BtcDataResponseGraph> {
    
    override suspend operator fun invoke(params: BtcGraphParams): Flow<NetworkState<BtcDataResponseGraph>> {
        return btcRepository.getBtcDetail(
            from = params.from,
            resolution = params.resolution,
            symbol = params.symbol,
            to = params.to
        )
    }
}

data class BtcGraphParams(
    val from: Long,
    val resolution: Int,
    val symbol: String,
    val to: Long
)