package com.example.btccompose.data.interceptor

import android.content.Context
import com.example.btccompose.R
import com.example.btccompose.utils.NetworkUtils
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

class BtcInterceptor @Inject constructor(
    private val networkUtils: NetworkUtils,
    private val context: Context
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!networkUtils.isNetworkAvailable()) {
            throw NoInternetException(context.getString(R.string.no_internet_error))
        }
        
        val request = chain.request()
        return chain.proceed(request)
    }
}

class NoInternetException(message: String) : IOException(message) 