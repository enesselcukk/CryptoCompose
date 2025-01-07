package com.example.btccompose.data.model

import com.google.gson.annotations.SerializedName

data class BTCDataResponse(
    @SerializedName("data") val data: List<Data>? = null,
    @SerializedName("success") val success: Boolean? = null,
    @SerializedName("message") val message: String? = null,
    @SerializedName("code") val code: Int? = null
)
