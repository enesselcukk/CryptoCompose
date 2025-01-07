package com.example.btccompose.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.btccompose.domain.model.BtcResponseMapper
import com.example.btccompose.domain.model.mapperResponse
import com.example.btccompose.domain.usecase.BtcLocalUseCase
import com.example.btccompose.domain.usecase.BtcLocalUseCaseParams
import com.example.btccompose.domain.usecase.BtcUseCase
import com.example.btccompose.presentation.viewmodel.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BtcViewModel @Inject constructor(
    private val btcUseCase: BtcUseCase,
    private val btcLocalUseCase: BtcLocalUseCase
) : BaseViewModel() {

    private val allData = MutableStateFlow<List<BtcResponseMapper>>(emptyList())
    private val btcList = MutableStateFlow<List<BtcResponseMapper>>(emptyList())

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val _getLocalBtc = MutableStateFlow<List<BtcResponseMapper>?>(null)
    val getLocalBtc = _getLocalBtc.asStateFlow()

    private val _favoriteIds = MutableStateFlow<Set<Int>>(emptySet())
    val favoriteIds: StateFlow<Set<Int>> = _favoriteIds

    private var currentPage = 1
    private val pageSize = 15
    private var isLoadingPaging = false
    private var hasMoreData = true

    private val _isLoadingMore = MutableStateFlow(false)
    val isLoadingMore = _isLoadingMore.asStateFlow()

    init {
        getLocal()
    }

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
    }

    val filteredBtc = combine(btcList, _searchQuery) { list, query ->
        if (query.isEmpty()) {
            list
        } else {
            list.filter {
                it.numeratorSymbol?.contains(query, ignoreCase = true) == true
            }
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )

    fun getBtcList() {
        viewModelScope.launch {
            isLoadingPaging = true
            currentPage = 1
            hasMoreData = true
            btcList.value = emptyList()
            _searchQuery.value = ""

            handleNetworkState(
                flow = btcUseCase.invoke(),
                onSuccess = { response ->
                    val mappedData = mapperResponse(response.data.orEmpty())
                    allData.value = mappedData
                    btcList.value = mappedData.take(pageSize)
                    hasMoreData = mappedData.size > pageSize
                    isLoadingPaging = false
                },
                onError = {
                    isLoadingPaging = false
                    btcList.value = emptyList()
                }
            )
        }
    }

    fun loadMoreData() {
        if (isLoadingPaging || hasMoreData.not()) return
        
        viewModelScope.launch {
            _isLoadingMore.value = true
            delay(200)
            
            val nextPageStartIndex = currentPage * pageSize
            val currentData = btcList.value
            val fullData = allData.value

            if (nextPageStartIndex < fullData.size) {
                val nextPageData = fullData.slice(
                    nextPageStartIndex until minOf(
                        nextPageStartIndex + pageSize,
                        fullData.size
                    )
                )
                btcList.value = currentData + nextPageData
                currentPage++
                hasMoreData = fullData.size > (currentPage * pageSize)
            } else {
                hasMoreData = false
            }
            
            _isLoadingMore.value = false
        }
    }

    fun getLocal() {
        viewModelScope.launch {
            handleNetworkState(
                flow = btcLocalUseCase.execute(BtcLocalUseCaseParams.GetAll),
                onSuccess = { data ->
                    _getLocalBtc.value = data
                    _favoriteIds.value = data.map { it.id }.toSet()
                }
            )
        }
    }

    fun loadFavoritesFromDb(ids: List<Int>) {
        viewModelScope.launch {
            handleNetworkState(
                flow = btcLocalUseCase.execute(BtcLocalUseCaseParams.GetFavorites(ids)),
                onSuccess = { data ->
                    _getLocalBtc.value = data
                    _favoriteIds.value = data.map { it.id }.toSet()
                }
            )
        }
    }

    fun toggleFavorite(btc: BtcResponseMapper) {
        viewModelScope.launch {
            handleNetworkState(
                flow = btcLocalUseCase.execute(BtcLocalUseCaseParams.Insert(btc)),
                onSuccess = {
                    _favoriteIds.value += setOf(btc.id)
                    getLocal()
                }
            )
        }
    }

    fun clearAllFavorites() {
        viewModelScope.launch {
            handleNetworkState(
                flow = btcLocalUseCase.execute(BtcLocalUseCaseParams.DeleteAll),
                onSuccess = {
                    _favoriteIds.value = emptySet()
                    _getLocalBtc.value = null
                }
            )
        }
    }

    fun deleteFavorite(id: Int) {
        viewModelScope.launch {
            handleNetworkState(
                flow = btcLocalUseCase.execute(BtcLocalUseCaseParams.DeleteById(id)),
                onSuccess = {
                    _favoriteIds.value -= setOf(id)
                    getLocal()
                }
            )
        }
    }

    fun resetSearch() {
        _searchQuery.value = ""
    }
}


