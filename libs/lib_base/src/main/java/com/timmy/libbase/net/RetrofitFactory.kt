package com.timmy.libbase.net

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitFactory private constructor() {

    private val retrofit: Retrofit

    companion object {
        const val URL_BASE = "https://wanandroid.com/"
        val instance: RetrofitFactory by lazy { RetrofitFactory() }
    }

    init {
        retrofit =
            Retrofit.Builder()
                .baseUrl(URL_BASE)
                .client(initOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    private fun initOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor()
                    .apply { setLevel(HttpLoggingInterceptor.Level.BODY) })
            .connectTimeout(5, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)
            .build()
    }

    fun <T> createService(service: Class<T>): T {
        return retrofit.create(service)
    }
}