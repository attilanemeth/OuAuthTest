package com.example.oauthtest.networking.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class BasicHeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder().apply {
                addHeader("Content-Type", "application/json")
                addHeader("Accept", "application/json")
            }
            .build()
        return chain.proceed(request)
    }
}