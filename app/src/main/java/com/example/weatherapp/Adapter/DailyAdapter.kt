package com.example.weatherapp.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.TempDisplaySettingManager
import com.example.weatherapp.data.Weather
import com.example.weatherapp.formatTemplateForDisplay
import com.example.weatherapp.databinding.ItemDailyWeatherBinding


class DailyWeatherViewHolder constructor(
    private val binding: ItemDailyWeatherBinding,
    private val tempDisplaySettingManager: TempDisplaySettingManager
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun from(parent: ViewGroup, tempDisplaySettingManager: TempDisplaySettingManager): DailyWeatherViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemDailyWeatherBinding.inflate(
                layoutInflater,
                parent,
                false
            )
            return DailyWeatherViewHolder(binding, tempDisplaySettingManager)
        }
    }

    private val tempText : TextView = binding.dailyTemp
    private val descriptionText : TextView = binding.dailyDescription
    private val dateText : TextView = binding.dailyDate
    private val image : ImageView = binding.dailyImage


    fun bind(weather: Weather){
        tempText.text = formatTemplateForDisplay(weather.temp, tempDisplaySettingManager.getTimeDisplaySetting())
        descriptionText.text = weather.description
        dateText.text = weather.date
        image.setImageResource(weather.icon)

    }
}
class DailyAdapter(
    private val tempDisplaySettingManager: TempDisplaySettingManager,

    private val clickHandler : (Weather) -> Unit
) : ListAdapter<Weather, DailyWeatherViewHolder>(DiffCallback) {

    companion object{

        val DiffCallback = object: DiffUtil.ItemCallback<Weather>(){
            override fun areItemsTheSame(oldItem: Weather, newItem: Weather): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Weather, newItem: Weather): Boolean {
                return oldItem == newItem
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyWeatherViewHolder {
        val binding = ItemDailyWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DailyWeatherViewHolder(binding, tempDisplaySettingManager)
    }

    override fun onBindViewHolder(holder: DailyWeatherViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener{
            clickHandler(getItem(position))
        }
    }
}