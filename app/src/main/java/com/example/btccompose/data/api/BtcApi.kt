package com.example.btccompose.data.api


import com.example.btccompose.data.model.BTCDataResponse
import retrofit2.http.GET


interface BtcApi {
    @GET("v2/ticker")
    suspend fun getBtc():BTCDataResponse
}