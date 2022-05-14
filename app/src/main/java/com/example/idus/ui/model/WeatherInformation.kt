package com.example.idus.ui.model

data class WeatherInformation(val location: String, val woeid: Int, val today: WeatherItem, val tomorrow: WeatherItem)

data class WeatherItem(val iconUrl: String, val temperature: Double, val humidity: Int, val weather: String)
