package com.example.btccompose.data.api


import com.example.btccompose.data.model.BtcDataResponseGraph
import retrofit2.http.GET
import retrofit2.http.Query

interface BtcGraphApi {
    @GET("v1/klines/history")
    suspend fun getBtcDetail(
        @Query("from") from: Long,
        @Query("resolution") resolution: Int,
        @Query("symbol") symbol: String,
        @Query("to") to: Long
    ):BtcDataResponseGraph

}