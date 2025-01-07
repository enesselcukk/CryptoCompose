package com.example.btccompose.navigation

import kotlinx.serialization.Serializable
import java.util.Calendar
import java.util.Date


@Serializable
data class DetailScreenModel(
    val symbol: String? =null,
    val denominatorSymbol:String?= null,
    val numeratorSymbol:String?,
    val resolution: Int = 60,
    val to: Long = Date().toEpochSecond(),
    val from: Long = Date().oneDayBefore().toEpochSecond()
)

fun Date.toEpochSecond(): Long = this.time / 1000

fun Date.oneDayBefore(): Date = Calendar.getInstance().apply {
    time = this@oneDayBefore
    add(Calendar.DAY_OF_YEAR, -1)
}.time