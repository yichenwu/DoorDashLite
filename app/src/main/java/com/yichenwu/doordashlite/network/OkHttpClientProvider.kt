package com.yichenwu.doordashlite.network

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

object OkHttpClientProvider {
    private const val TIME_OUT = 20000L

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .callTimeout(TIME_OUT, TimeUnit.MILLISECONDS)   // time out after 20 seconds
            .build()
    }

    fun get() = okHttpClient
}
