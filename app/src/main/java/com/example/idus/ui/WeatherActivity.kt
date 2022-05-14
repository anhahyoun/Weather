package com.example.idus.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.idus.R
import com.example.idus.databinding.ActivityWeatherBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWeatherBinding
    private val viewModel: WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        setupObserve()
    }

    private fun initView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_weather)
        binding.lifecycleOwner = this
    }

    private fun setupObserve() {
        viewModel.weatherInformation.observe(this) {

        }
    }
}