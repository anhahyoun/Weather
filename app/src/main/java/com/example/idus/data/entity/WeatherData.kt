package com.example.idus.data.entity

import com.google.gson.annotations.SerializedName

data class WeatherData(
    val consolidated_weather: List<ConsolidatedWeather>,
) {
    data class ConsolidatedWeather(
        val humidity: Int,
        @SerializedName("the_temp")
        val temperature: Double,
        @SerializedName("weather_state_abbr")
        val weatherStateAbbr: String,
        @SerializedName("weather_state_name")
        val weatherStateName: String
    )
}