package com.example.idus.data.datasource

import com.example.idus.data.entity.LocationData
import com.example.idus.data.entity.WeatherData

interface DataSource {

    suspend fun getLocations(query: String): Result<List<LocationData>>

    suspend fun getWeather(weoid: Int): Result<WeatherData>
}