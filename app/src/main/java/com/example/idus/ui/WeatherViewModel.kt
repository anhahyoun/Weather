package com.example.idus.ui

import androidx.lifecycle.ViewModel
import com.example.idus.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(val repository: Repository) : ViewModel() {

}