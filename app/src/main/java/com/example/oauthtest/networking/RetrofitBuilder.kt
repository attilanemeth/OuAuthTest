package com.example.oauthtest.networking

import android.app.Application
import android.content.Context
import co.infinum.retromock.Retromock
import com.example.oauthtest.networking.interceptors.BasicHeaderInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

object RetrofitBuilder {

    fun makeOkHttpClient(application: Application): OkHttpClient {
        val defaultTimeOut = 10000.toLong()
        return OkHttpClient.Builder()
            .connectTimeout(defaultTimeOut, TimeUnit.MILLISECONDS)
            .addInterceptor(BasicHeaderInterceptor())
            .build()
    }

    inline fun <reified T>  makeRetrofit(context: Context, okHttpClient: OkHttpClient): T {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://test.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        return retrofit.create();
    }

    fun makeRetroMock(retrofit: Retrofit):Retromock{
        return Retromock.Builder().retrofit(retrofit).build()
    }
}