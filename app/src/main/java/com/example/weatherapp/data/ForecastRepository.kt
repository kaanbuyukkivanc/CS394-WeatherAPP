package com.example.weatherapp.data

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.R
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Date


class ForecastRepository {

    private val _weeklyForecast = MutableLiveData<List<Weather>>()
    val weeklyForecast: LiveData<List<Weather>> = _weeklyForecast

    private val _dailyForecast = MutableLiveData<List<Weather>>()
    val dailyForecast: LiveData<List<Weather>> = _dailyForecast

    private val dateFormat = SimpleDateFormat("dd-MM-yyyy")

    private lateinit var forecastTemp: List<Float>

    private val current = Date()


    @RequiresApi(Build.VERSION_CODES.O)
    fun loadForecastWeekly(zipcode: String) {
        when (zipcode) {
            "11111" -> {
                forecastTemp = listOf(0.3f, 5.2f, 8.5f, 10.4f, 30.5f, 75f, 90.2f)

            }

            "22222" -> {
                forecastTemp = listOf(45.7f, 23.1f, 10.3f, 3f, 67.4f, 12.2f, 2f)
            }

            "33333" -> {
                forecastTemp =
                    listOf(5.3f, 26.8f, 15.4f, 40f, 57.1f, 30.3f, 64.2f)
            }

            else -> {
                forecastTemp =
                    listOf(19.3f, 28.1f, 76.4f, 65.2f, 12.5f, 17f , 12f)
            }
        }
        val forecastItems = forecastTemp.map {
            Weather(it, getTempDesc(it), updateDate(it), updateUI(getTempDesc(it)))

        }
        _weeklyForecast.value = forecastItems
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun loadForecastDaily(zipcode: String) {
        when (zipcode) {
            "11111" -> {
                forecastTemp = listOf(0.3f)

            }

            "22222" -> {
                forecastTemp = listOf(45.7f)
            }

            "33333" -> {
                forecastTemp =
                    listOf(5.3f)
            }

            else -> {
                forecastTemp =
                    listOf(19.3f)
            }
        }
        val forecastItems = forecastTemp.map {
            Weather(it, getTempDesc(it), updateDate(it), updateUI(getTempDesc(it)))

        }
        _dailyForecast.value = forecastItems

    }


    private fun getTempDesc(temp: Float): String {
        return when (temp) {
            in Float.MIN_VALUE.rangeTo(0f) -> "Snowy"
            in 0f.rangeTo(20f) -> "Thunder"
            in 20f.rangeTo(35f) -> "Rainy"
            in 35f.rangeTo(50f) -> "Foggy"
            in 50f.rangeTo(75f) -> "Cloudy"
            in 75f.rangeTo(100f) -> "Sunny"
            else -> "Does not compute"
        }
    }

    private fun updateUI(description: String): Int {
        return when (description) {
            "Snowy" -> R.drawable.snow
            "Thunder" -> R.drawable.thunder
            "Rainy" -> R.drawable.rain
            "Foggy" -> R.drawable.fog
            "Cloudy" -> R.drawable.cloud
            "Sunny" -> R.drawable.sun
            else -> 0

        }
    }

    @SuppressLint("NewApi")
    val currentDate = LocalDate.now()

    @SuppressLint("NewApi")
    private fun updateDate(temp : Float) : String{
        return when (temp){
            forecastTemp.get(0) -> currentDate.toString()
            forecastTemp.get(1) -> currentDate.plusDays(1).toString()
            forecastTemp.get(2) -> currentDate.plusDays(2).toString()
            forecastTemp.get(3) -> currentDate.plusDays(3).toString()
            forecastTemp.get(4) -> currentDate.plusDays(4).toString()
            forecastTemp.get(5) -> currentDate.plusDays(5).toString()
            forecastTemp.get(6) -> currentDate.plusDays(6).toString()
            else -> ""
        }
    }


}
