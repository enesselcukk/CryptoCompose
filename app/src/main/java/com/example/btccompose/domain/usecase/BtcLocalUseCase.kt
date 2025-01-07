package com.example.btccompose.domain.usecase

import com.example.btccompose.domain.model.BtcResponseMapper
import com.example.btccompose.domain.repository.BtcRepositoryLocal
import com.example.btccompose.utils.NetworkState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class BtcLocalUseCase @Inject constructor(
    private val btcRepositoryLocal: BtcRepositoryLocal
) {
    suspend fun <T> execute(params: BtcLocalUseCaseParams<T>): Flow<NetworkState<T>> = when (params) {
        is BtcLocalUseCaseParams.Insert -> btcRepositoryLocal.insertBtc(params.btc) as Flow<NetworkState<T>>
        is BtcLocalUseCaseParams.GetById -> btcRepositoryLocal.getById(params.id) as Flow<NetworkState<T>>
        is BtcLocalUseCaseParams.GetFavorites -> btcRepositoryLocal.getFavoritesByIds(params.ids) as Flow<NetworkState<T>>
        is BtcLocalUseCaseParams.GetAll -> btcRepositoryLocal.getLocalBtc() as Flow<NetworkState<T>>
        is BtcLocalUseCaseParams.DeleteAll -> btcRepositoryLocal.deleteAll() as Flow<NetworkState<T>>
        is BtcLocalUseCaseParams.DeleteById -> btcRepositoryLocal.deleteById(params.id) as Flow<NetworkState<T>>
    }
}

sealed class BtcLocalUseCaseParams<out T> {
    data class Insert(val btc: BtcResponseMapper) : BtcLocalUseCaseParams<Unit>()
    data class GetById(val id: Int) : BtcLocalUseCaseParams<BtcResponseMapper?>()
    data class GetFavorites(val ids: List<Int>) : BtcLocalUseCaseParams<List<BtcResponseMapper>>()
    data class DeleteById(val id: Int) : BtcLocalUseCaseParams<Unit>()
    data object GetAll : BtcLocalUseCaseParams<List<BtcResponseMapper>>()
    data object DeleteAll : BtcLocalUseCaseParams<Unit>()
}