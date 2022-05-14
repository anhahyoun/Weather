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
    private lateinit var adapter: WeatherAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        setupObserve()
        // TODO loading page, refresh
    }

    private fun initView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_weather)
        binding.lifecycleOwner = this
        adapter = WeatherAdapter()
        binding.rvWeather.adapter = adapter
    }

    private fun setupObserve() {
        viewModel.weatherInformation.observe(this) {
            adapter.submitList(it)
        }
    }
}