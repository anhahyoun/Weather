package com.example.idus.ui

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
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _weatherInformation = MutableLiveData<List<WeatherInformation>>()
    val weatherInformation: LiveData<List<WeatherInformation>> = _weatherInformation

    private val _isError = MutableSharedFlow<Boolean>()
    val isError = _isError.asSharedFlow()

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _showLoading = MutableLiveData<Boolean>()
    val showLoading: LiveData<Boolean> = _showLoading

    init {
        getLocation("se", false)
    }

    fun getLocation(query: String, isRefresh: Boolean) {
        _isLoading.postValue(true)
        _showLoading.postValue(!isRefresh)
        viewModelScope.launch {
            val result = repository.getLocations(query)
            result.mapCatching {
                val data = it.map {
                    async {
                        val weatherResult = getWeather(it.woeid).consolidated_weather
                        val today = weatherResult[0].run {
                            WeatherItem(
                                "https://www.metaweather.com/static/img/weather/png/64/${weatherStateAbbr}.png",
                                temperature.toInt(),
                                humidity,
                                weatherStateName
                            )
                        }
                        val tomorrow = weatherResult[1].run {
                            WeatherItem(
                                "https://www.metaweather.com/static/img/weather/png/64/${weatherStateAbbr}.png",
                                temperature.toInt(),
                                humidity,
                                weatherStateName
                            )
                        }
                        WeatherInformation(it.title, it.woeid, today, tomorrow)
                    }
                }.awaitAll()
                _weatherInformation.postValue(data)
            }.onFailure {
                _isError.emit(true)
            }
            _isLoading.postValue(false)
            _showLoading.postValue(false)
        }
    }

    private suspend fun getWeather(woeid: Int): WeatherData {
        return repository.getWeather(woeid).getOrThrow()
    }
}