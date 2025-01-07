package com.example.btccompose.domain.model

import com.example.btccompose.data.model.Data


fun mapperResponse(
    data: List<Data>
): List<BtcResponseMapper> {
    return data.map {
        BtcResponseMapper(
            id = it.pair.hashCode(),
            pair = it.pair.orEmpty(),
            denominatorSymbol = it.denominatorSymbol,
            last = it.last.toString(),
            dailyPercent = it.dailyPercent.toString(),
            volume = it.volume.toString(),
            numeratorSymbol = it.numeratorSymbol.toString(),
            timestamp = it.timestamp
        )
    }
}