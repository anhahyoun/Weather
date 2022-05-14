package com.example.idus.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.idus.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    fun getLocation(query: String) {
        viewModelScope.launch {
            val result = repository.getLocations(query)
            result.onSuccess {
                // TODO
            }.onFailure {
                // TODO
            }
        }
    }

    fun getWeather(woeid: Int) {
        viewModelScope.launch {
            val result = repository.getWeather(woeid)
            result.onSuccess {
                // TODO
            }.onFailure {
                // TODO
            }
        }
    }

}