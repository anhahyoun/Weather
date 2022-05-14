package com.example.idus.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.idus.R
import com.example.idus.databinding.ActivityWeatherBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class WeatherActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWeatherBinding
    private val viewModel: WeatherViewModel by viewModels()
    private lateinit var adapter: WeatherAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        setupObserve()
        setupListener()
        // TODO loading page
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
            binding.srWeather.isRefreshing = false
        }
        lifecycleScope.launchWhenStarted {
            viewModel.isError.collect {
                Toast.makeText(applicationContext, getString(R.string.error_msg), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupListener() {
        binding.srWeather.setOnRefreshListener {
            viewModel.getLocation("se")
        }
    }
}