package com.example.idus.data.repository

import com.example.idus.data.datasource.DataSource
import com.example.idus.data.entity.LocationData
import com.example.idus.data.entity.WeatherData
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val remote: DataSource) : Repository {
    override suspend fun getLocations(query: String): Result<List<LocationData>> = withContext(IO) {
        remote.getLocations(query)
    }

    override suspend fun getWeather(woeid: Int): Result<WeatherData> = withContext(IO) {
        remote.getWeather(woeid)
    }
}