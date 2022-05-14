package com.example.idus.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.idus.R
import com.example.idus.databinding.ItemWeatherBinding
import com.example.idus.ui.model.WeatherInformation

class WeatherAdapter : ListAdapter<WeatherInformation, WeatherAdapter.ViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemWeatherBinding.bind(LayoutInflater.from(parent.context).inflate(R.layout.item_weather, parent, false))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: ItemWeatherBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: WeatherInformation) {
            binding.item = item
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<WeatherInformation>() {
            override fun areItemsTheSame(oldItem: WeatherInformation, newItem: WeatherInformation): Boolean {
                return oldItem.woeid == newItem.woeid
            }

            override fun areContentsTheSame(oldItem: WeatherInformation, newItem: WeatherInformation): Boolean {
                return oldItem == newItem
            }
        }
    }
}