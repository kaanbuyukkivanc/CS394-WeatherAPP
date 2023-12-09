package com.example.weatherapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.data.Weather


class DailyWeatherViewHolder(view : View) : RecyclerView.ViewHolder(view){

    private val tempText : TextView = view.findViewById(R.id.temperatureText)
    private val descriptionText : TextView = view.findViewById(R.id.descriptionText)

    fun bind(weather: Weather){
        tempText.text = String.format("%.1f",weather.temp)
        descriptionText.text = weather.description
    }
}
class DailyAdapter(
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
        return DailyWeatherViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DailyWeatherViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener{
            clickHandler(getItem(position))
        }
    }
}