package com.example.btccompose.domain.usecase

import com.example.btccompose.data.model.BTCDataResponse
import com.example.btccompose.domain.base.BaseUseCaseNoParams
import com.example.btccompose.domain.repository.BtcRepository
import com.example.btccompose.utils.NetworkState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BtcUseCase @Inject constructor(
    private val btcRepository: BtcRepository
) : BaseUseCaseNoParams<BTCDataResponse> {
    
    override suspend operator fun invoke(): Flow<NetworkState<BTCDataResponse>> {
        return btcRepository.getBtc()
    }
}