package com.example.idus.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.idus.data.entity.WeatherData
import com.example.idus.data.repository.Repository
import com.example.idus.ui.model.WeatherInformation
import com.example.idus.ui.model.WeatherItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _weatherInformation = MutableLiveData<List<WeatherInformation>>()
    val weatherInformation: LiveData<List<WeatherInformation>> = _weatherInformation

    init {
        getLocation("se")
    }

    private fun getLocation(query: String) {
        viewModelScope.launch {
            val result = repository.getLocations(query)
            result.mapCatching {
                val data = it.map {
                    async {
                        val weatherResult = getWeather(it.woeid).consolidated_weather
                        val today = weatherResult[0].run {
                            WeatherItem(
                                "https://www.metaweather.com/static/img/weather/${weatherStateAbbr}.svg",
                                temperature,
                                humidity,
                                weatherStateName
                            )
                        }
                        val tomorrow = weatherResult[1].run {
                            WeatherItem(
                                "https://www.metaweather.com/static/img/weather/${weatherStateAbbr}.svg",
                                temperature,
                                humidity,
                                weatherStateName
                            )
                        }
                        WeatherInformation(it.title, it.woeid, today, tomorrow)
                    }
                }.awaitAll()
                _weatherInformation.postValue(data)
            }.onFailure {
                // TODO
                Log.e("failure", it.toString())
            }
        }
    }

    private suspend fun getWeather(woeid: Int): WeatherData {
        return repository.getWeather(woeid).getOrThrow()
    }
}