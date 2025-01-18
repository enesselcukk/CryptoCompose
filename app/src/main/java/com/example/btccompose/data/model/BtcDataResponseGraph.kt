package com.example.btccompose.data.model

import com.google.gson.annotations.SerializedName

data class BtcDataResponseGraph(
    @SerializedName("s") val s: String? = null,
    @SerializedName("t") val t: List<Any>? = null,
    @SerializedName("h") val h: List<Any>? = null,
    @SerializedName("o") val o: List<Any>? = null,
    @SerializedName("l") val l: List<Any>? = null,
    @SerializedName("c") val c: List<Any>? = null,
    @SerializedName("v") val v: List<Any>? = null
    )
