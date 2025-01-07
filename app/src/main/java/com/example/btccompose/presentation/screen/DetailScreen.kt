package com.example.btccompose.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.btccompose.navigation.DetailScreenModel
import com.example.btccompose.presentation.component.LineChart
import com.example.btccompose.presentation.screen.base.BaseScreen
import com.example.btccompose.presentation.viewmodel.BtcGraphViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun DetailScreen(
    detailScreenModel: DetailScreenModel,
    btcGraphViewModel: BtcGraphViewModel = hiltViewModel(),
) {

    BaseScreen(viewModel = btcGraphViewModel, onRetry = {
        btcGraphViewModel.getBtcGraph(detailScreenModel)
    }) {

        val graphData by btcGraphViewModel.getBtcGraph.collectAsStateWithLifecycle()

        LaunchedEffect(Unit) {
            btcGraphViewModel.getBtcGraph(detailScreenModel)
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            graphData?.let { data ->
                val prices = data.c?.mapNotNull { it.toString().toFloatOrNull() }.orEmpty()
                val timeLabels = data.t?.map { it.toTimeLabel() }.orEmpty()

                if (prices.isNotEmpty() && timeLabels.isNotEmpty()) {
                    LineChart(
                        dataPoints = prices,
                        xLabels = timeLabels
                    )
                }
            }
        }
    }
}

private fun Any?.toTimeLabel(): String {
    return try {
        this?.toString()?.toDoubleOrNull()?.toLong()?.let { timestamp ->
            Date(timestamp * 1000).let { date ->
                SimpleDateFormat("HH:mm", Locale.getDefault()).format(date)
            }
        } ?: throw Exception("Invalid timestamp format")
    } catch (e: Exception) {
        throw Exception("Failed to convert timestamp: ${this.toString()}. Please check data format.")
    }
}
