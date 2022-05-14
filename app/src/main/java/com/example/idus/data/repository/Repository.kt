package com.example.idus.data.repository

import com.example.idus.data.entity.LocationData
import com.example.idus.data.entity.WeatherData

interface Repository {

    suspend fun getLocations(query: String): Result<List<LocationData>>

    suspend fun getWeather(woeid: Int): Result<WeatherData>
}