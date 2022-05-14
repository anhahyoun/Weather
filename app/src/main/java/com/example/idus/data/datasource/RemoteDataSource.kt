package com.example.idus.data.datasource

import com.example.idus.data.api.Service
import com.example.idus.data.entity.LocationData
import com.example.idus.data.entity.WeatherData
import retrofit2.HttpException
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val service: Service) : DataSource {
    override suspend fun getLocations(query: String): Result<List<LocationData>> {
        val result = runCatching { service.getLocations(query) }

        return when (val exception = result.exceptionOrNull()) {
            null -> result
            is HttpException -> Result.failure(ErrorData.HttpError(exception.message(), exception.code()))
            else -> Result.failure(exception)
        }
    }

    override suspend fun getWeather(weoid: Int): Result<WeatherData> {
        val result = runCatching { service.getLocationWeather(weoid) }

        return when (val exception = result.exceptionOrNull()) {
            null -> result
            is HttpException -> Result.failure(ErrorData.HttpError(exception.message(), exception.code()))
            else -> Result.failure(exception)
        }
    }
}