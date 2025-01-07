package com.example.btccompose.di

import android.content.Context
import com.example.btccompose.BuildConfig
import com.example.btccompose.data.api.BtcApi
import com.example.btccompose.data.api.BtcGraphApi
import com.example.btccompose.utils.NetworkUtils
import com.google.gson.Gson
import io.mockk.every
import io.mockk.mockk
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit

class AppModuleTest {

    private lateinit var context: Context
    private lateinit var networkUtils: NetworkUtils
    private lateinit var okHttpClient: OkHttpClient

    @Before
    fun setup() {
        context = mockk(relaxed = true)
        networkUtils = mockk(relaxed = true)
        okHttpClient = mockk(relaxed = true)
    }

    @Test
    fun `test provideNetworkUtils returns NetworkUtils instance`() {
        val result = AppModule.provideNetworkUtils(context)
        assertNotNull(result)
    }

    @Test
    fun `test provideOkhttpClient configures client correctly`() {
        val result = AppModule.provideOkhttpClient(networkUtils)
        
        assertNotNull(result)
        assertEquals(120L * 1000, result.connectTimeoutMillis.toLong())
        assertEquals(120L * 1000, result.readTimeoutMillis.toLong())
        assertEquals(120L * 1000, result.writeTimeoutMillis.toLong())
        assert(result.interceptors.any { it is HttpLoggingInterceptor })
    }

    @Test
    fun `test gson provides Gson instance`() {
        val result = AppModule.gson()
        assertNotNull(result)
        assert(result is Gson)
    }

    @Test
    fun `test provideRetrofitBuilder returns configured builder`() {
        val result = AppModule.provideRetrofitBuilder(okHttpClient)
        
        assertNotNull(result)
    }

    @Test
    fun `test provideGetBtc creates BtcApi with correct base url`() {
        val retrofitBuilder = mockk<Retrofit.Builder>()
        val retrofit = mockk<Retrofit>()
        val btcApi = mockk<BtcApi>()

        every { retrofitBuilder.baseUrl(BuildConfig.BASE_URL) } returns retrofitBuilder
        every { retrofitBuilder.build() } returns retrofit
        every { retrofit.create(BtcApi::class.java) } returns btcApi

        val result = AppModule.provideGetBtc(retrofitBuilder)
        assertEquals(btcApi, result)
    }

    @Test
    fun `test provideGetGraph creates BtcGraphApi with correct base url`() {
        val retrofitBuilder = mockk<Retrofit.Builder>()
        val retrofit = mockk<Retrofit>()
        val btcGraphApi = mockk<BtcGraphApi>()

        every { retrofitBuilder.baseUrl(BuildConfig.GRAPH_BASE_URL) } returns retrofitBuilder
        every { retrofitBuilder.build() } returns retrofit
        every { retrofit.create(BtcGraphApi::class.java) } returns btcGraphApi

        val result = AppModule.provideGetGraph(retrofitBuilder)
        assertEquals(btcGraphApi, result)
    }
}
