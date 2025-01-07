package com.example.btccompose.di


import com.example.btccompose.BuildConfig
import com.example.btccompose.data.api.BtcApi
import com.example.btccompose.data.api.BtcGraphApi
import com.example.btccompose.utils.NetworkUtils
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton
import android.content.Context
import com.example.btccompose.data.interceptor.BtcInterceptor

@[Module InstallIn(SingletonComponent::class)]
object AppModule {
    private const val CONNECTION_TIMEOUT = 120L
    private const val READ_TIMEOUT = 120L
    private const val WRITE_TIMEOUT = 120L

    @Provides
    @Singleton
    fun provideNetworkUtils(@ApplicationContext context: Context): NetworkUtils {
        return NetworkUtils(context)
    }

    @Provides
    @Singleton
    fun provideOkhttpClient(
        networkUtils: NetworkUtils,
        @ApplicationContext context: Context
    ): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
        }
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(BtcInterceptor(networkUtils,context))
            .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun gson(): Gson = GsonBuilder()
        .setLenient()
        .create()

    @Provides
    @Singleton
    fun provideRetrofitBuilder(okHttpClient: OkHttpClient): Retrofit.Builder {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson()))
            .client(okHttpClient)
    }

    @Singleton
    @Provides
    fun provideGetBtc(retrofitBuilder: Retrofit.Builder): BtcApi {
        return retrofitBuilder
            .baseUrl(BuildConfig.BASE_URL)
            .build()
            .create(BtcApi::class.java)
    }

    @Singleton
    @Provides
    fun provideGetGraph(retrofitBuilder: Retrofit.Builder): BtcGraphApi {
        return retrofitBuilder
            .baseUrl(BuildConfig.GRAPH_BASE_URL)
            .build()
            .create(BtcGraphApi::class.java)
    }
}