package com.example.weatherapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.TempDisplaySetting
import com.example.weatherapp.TempDisplaySettingManager
import com.example.weatherapp.data.Weather
import com.example.weatherapp.formatTemplateForDisplay


class DailyWeatherViewHolder(view : View, private val tempDisplaySettingManager: TempDisplaySettingManager) : RecyclerView.ViewHolder(view){

    private val tempText : TextView = view.findViewById(R.id.dailyTemp)
    private val descriptionText : TextView = view.findViewById(R.id.dailyDescription)
    private val dateText : TextView = view.findViewById(R.id.dailyDate)
    private val image : ImageView = view.findViewById(R.id.dailyImage)


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
) : ListAdapter<Weather, DailyWeatherViewHolder>(DIFF_CONFIG) {

    companion object{

        val DIFF_CONFIG = object: DiffUtil.ItemCallback<Weather>(){
            override fun areItemsTheSame(oldItem: Weather, newItem: Weather): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Weather, newItem: Weather): Boolean {
                return oldItem == newItem
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyWeatherViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_daily_weather, parent, false)
        return DailyWeatherViewHolder(itemView, tempDisplaySettingManager)
    }

    override fun onBindViewHolder(holder: DailyWeatherViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener{
            clickHandler(getItem(position))
        }
    }
}