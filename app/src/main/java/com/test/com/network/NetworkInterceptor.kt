package com.test.com.network

import com.test.com.util.Constants
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class NetworkInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("Authorization", "Token " + Constants.TOKEN_FOR_NETWORKING)
            .build()
        return chain.proceed(request)
    }
}