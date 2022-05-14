package com.example.idus.data.api

import com.example.idus.data.entity.LocationData
import com.example.idus.data.entity.WeatherData
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface Service {

    @GET("location/search")
    suspend fun getLocations(
        @Query("query") query: String
    ): List<LocationData>

    @GET("location/{woeid}/")
    suspend fun getLocationWeather(
        @Path("woeid") woeid: Int
    ): WeatherData

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