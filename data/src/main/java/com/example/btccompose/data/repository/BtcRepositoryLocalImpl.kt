package com.example.btccompose.data.repository

import com.example.btccompose.data.database.BtcDao
import com.example.btccompose.di.IoDispatcher
import com.example.btccompose.domain.model.BtcResponseMapper
import com.example.btccompose.domain.repository.BtcRepositoryLocal
import com.example.btccompose.utils.NetworkState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class BtcRepositoryLocalImpl @Inject constructor(
    private val btcDao: BtcDao,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : BtcRepositoryLocal {
    override suspend fun getLocalBtc(): Flow<NetworkState<List<BtcResponseMapper>>> = flow {
        emit(NetworkState.Loading)
        try {
            val dao = btcDao.allBtc()
            emit(NetworkState.Success(dao))
        } catch (ex: Exception) {
            emit(NetworkState.Error(ex))
        } finally {
            emit(NetworkState.Completed)
        }
    }.flowOn(dispatcher)

    override suspend fun insertBtc(btcResponseMapper: BtcResponseMapper): Flow<NetworkState<Unit>> =
        flow {
            emit(NetworkState.Loading)
            try {
                val existingItem = btcDao.getById(btcResponseMapper.id)
                if (existingItem != null) {
                    val newFavoriteStatus = !existingItem.isFavorite
                    val result = btcDao.updateFavoriteStatus(btcResponseMapper.id, newFavoriteStatus)
                    if (result > 0) {
                        emit(NetworkState.Success(Unit))
                    } else {
                        emit(NetworkState.Error(Exception("Failed to update favorite status")))
                    }
                } else {
                    btcResponseMapper.isFavorite = true
                    val rowId = btcDao.insertBtc(btcResponseMapper)
                    if (rowId != -1L) {
                        emit(NetworkState.Success(Unit))
                    } else {
                        emit(NetworkState.Error(Exception("Failed to add to favorites")))
                    }
                }
            } catch (ex: Exception) {
                emit(NetworkState.Error(ex))
            } finally {
                emit(NetworkState.Completed)
            }
        }.flowOn(dispatcher)


    override suspend fun getById(id: Int): Flow<NetworkState<BtcResponseMapper?>> = flow {
        emit(NetworkState.Loading)
        try {
            val result = btcDao.getById(id)
            emit(NetworkState.Success(result))
        } catch (ex: Exception) {
            emit(NetworkState.Error(ex))
        } finally {
            emit(NetworkState.Completed)
        }
    }.flowOn(dispatcher)

    override suspend fun getFavoritesByIds(ids: List<Int>): Flow<NetworkState<List<BtcResponseMapper>>> =
        flow {
            emit(NetworkState.Loading)
            try {
                val result = btcDao.getFavoritesByIds(ids)
                emit(NetworkState.Success(result))
            } catch (ex: Exception) {
                emit(NetworkState.Error(ex))
            } finally {
                emit(NetworkState.Completed)
            }
        }.flowOn(dispatcher)

    override suspend fun deleteAll(): Flow<NetworkState<Unit>> = flow {
        emit(NetworkState.Loading)
        try {
            btcDao.deleteAll()
            emit(NetworkState.Success(Unit))
        } catch (ex: Exception) {
            emit(NetworkState.Error(ex))
        } finally {
            emit(NetworkState.Completed)
        }
    }.flowOn(dispatcher)

    override suspend fun deleteById(id: Int): Flow<NetworkState<Unit>> = flow {
        emit(NetworkState.Loading)
        try {
            val result = btcDao.deleteID(id)
            if (result > 0) {
                emit(NetworkState.Success(Unit))
            } else {
                emit(NetworkState.Error(Exception("Failed to delete item")))
            }
        } catch (ex: Exception) {
            emit(NetworkState.Error(ex))
        } finally {
            emit(NetworkState.Completed)
        }
    }.flowOn(dispatcher)

}