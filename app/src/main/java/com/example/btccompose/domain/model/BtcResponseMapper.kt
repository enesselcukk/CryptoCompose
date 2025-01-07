package com.example.btccompose.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.btccompose.utils.formatVolume
import com.google.gson.annotations.SerializedName

@Entity("btc_entity")
data class BtcResponseMapper(
    @PrimaryKey @field:SerializedName("id") val id: Int,
    val pair: String? = null,
    val denominatorSymbol: String? = null,
    val last: String? = null,
    val dailyPercent: String? = null,
    val volume: String? = null,
    val numeratorSymbol: String? = null,
    val timestamp: Long? = null,
    var rawNewPercent: String? = null,
    var formatVolume: String? = null,
    var formatLast: String? = null,
    var isFavorite: Boolean = false
) {
    var newPercent: String?
        get() = if (dailyPercent?.startsWith("-") == true) {
            dailyPercent.removePrefix("-")
        } else {
            dailyPercent
        }
        set(value) {
            rawNewPercent = value
        }

    var formattedVolume: String?
        get() = volume?.formatVolume()
        set(value) {
            formatVolume = value
        }

    var formattedLast: String?
        get() = last?.formatVolume()
        set(value) {
            formatLast = value
        }
}

