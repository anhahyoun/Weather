package com.example.idus.data.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

interface Service {

    companion object {
        private const val BASE_RUL = "https://www.metaweather.com/api/"

        fun create(): Service {
            val client = OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build()

            return Retrofit.Builder().baseUrl(BASE_RUL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(Service::class.java)
        }
    }
}