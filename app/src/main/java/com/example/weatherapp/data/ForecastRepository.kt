package com.example.weatherapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.sql.Types.FLOAT
import kotlin.random.Random

class ForecastRepository {

    private val _weeklyForecast = MutableLiveData<List<Weather>>()
    val weeklyForecast : LiveData<List<Weather>> = _weeklyForecast

    fun loadForecast(cityName : String){
        val randomValues = List(10){ (Random.nextFloat().rem(100)*100) }
        val forecastItems = randomValues.map{
            Weather(it,getTempDesc(it))
        }
        _weeklyForecast.setValue(forecastItems)
    }


    private fun getTempDesc(temp : Float) : String{
        return when (temp) {
            in Float.MIN_VALUE.rangeTo(0f) -> "Cold"
            in 0f.rangeTo(50f) -> "Cold but okay"
            in 50f.rangeTo(75f) -> "Warmer"
            in 75f.rangeTo(100f) -> "Too hot"
            else -> "Does not compute"
        }
    }
}