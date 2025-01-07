package com.example.btccompose.data.model

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("pair") val pair: String? = null,
    @SerializedName("pairNormalized") val pairNormalized: String? = null,
    @SerializedName("timestamp") val timestamp: Long? = null,
    @SerializedName("last") val last: Any? = null,
    @SerializedName("high") val high: Any? = null,
    @SerializedName("low") val low: Any? = null,
    @SerializedName("bid") val bid: Any? = null,
    @SerializedName("ask") val ask: Any? = null,
    @SerializedName("open") val open: Any? = null,
    @SerializedName("volume") val volume: Any? = null,
    @SerializedName("average") val average: Any? = null,
    @SerializedName("daily") val daily: Any? = null,
    @SerializedName("dailyPercent") val dailyPercent: Any? = null,
    @SerializedName("denominatorSymbol") val denominatorSymbol: String? = null,
    @SerializedName("numeratorSymbol") val numeratorSymbol: String? = null,
    @SerializedName("order") val order: Int? = null
)
