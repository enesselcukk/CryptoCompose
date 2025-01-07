package com.example.btccompose.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.btccompose.data.model.BtcDataResponseGraph
import com.example.btccompose.domain.usecase.BtcGraphParams
import com.example.btccompose.domain.usecase.BtcGraphUseCase
import com.example.btccompose.navigation.DetailScreenModel
import com.example.btccompose.presentation.viewmodel.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BtcGraphViewModel @Inject constructor(private val btcUseCase: BtcGraphUseCase) :
    BaseViewModel() {
    private val _getBtcGraph = MutableStateFlow<BtcDataResponseGraph?>(null)
    val getBtcGraph = _getBtcGraph.asStateFlow()

    fun getBtcGraph(model: DetailScreenModel) {
        viewModelScope.launch {
            handleNetworkState(btcUseCase.invoke(
                BtcGraphParams(model.from, model.resolution, model.numeratorSymbol.orEmpty(), model.to)
            ),
                onSuccess = {
                    _getBtcGraph.value = it
                })
        }
    }

}