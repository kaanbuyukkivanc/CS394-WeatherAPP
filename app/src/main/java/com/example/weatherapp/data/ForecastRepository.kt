package com.example.weatherapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.sql.Types.FLOAT
import kotlin.random.Random

class ForecastRepository {

    private val _weeklyForecast = MutableLiveData<List<Weather>>()
    val weeklyForecast : LiveData<List<Weather>> = _weeklyForecast

    private val _dailyForecast = MutableLiveData<List<Weather>>()
    val dailyForecast : LiveData<List<Weather>> = _dailyForecast

    private lateinit var forecastTemp:List<Float>
    fun loadForecastWeekly(zipcode : String) {
        /*val randomValues = List(10){ (Random.nextFloat().rem(100)*100) }
        val forecastItems = randomValues.map{
            Weather(it,getTempDesc(it))
        }
        _weeklyForecast.setValue(forecastItems)
        */

        when (zipcode) {
            "11111" -> {
                forecastTemp = listOf(0.3f, 5.2f, 8.5f, 10.4f, 30.5f, 75f, 90.2f, 65.8f, 25.1f, 13f)
            }

            "22222" -> {
                forecastTemp = listOf(45.7f, 23.1f, 10.3f, 3f, 67.4f, 12.2f, 2f, 56.8f, 11.4f, 85f)
            }

            "33333" -> {
                forecastTemp =
                    listOf(5.3f, 26.8f, 15.4f, 40f, 57.1f, 30.3f, 64.2f, 10.8f, 11.4f, 0f)
            }

            else -> {
                forecastTemp =
                    listOf(19.3f, 28.1f, 76.4f, 65.2f, 12.5f, 17f, 78.9f, 39f, 70.3f, 99f)
            }
        }
        val forecastItems = forecastTemp.map {
            Weather(it, getTempDesc(it))

        }
        _weeklyForecast.value = forecastItems
    }

        fun loadForecastDaily(zipcode: String) {
            /*
            val randomValues = List(10) { (Random.nextFloat().rem(100) * 100) }
            val forecastItems = randomValues.map {
                Weather(it, getTempDesc(it))
            }
            _weeklyForecast.setValue(forecastItems)

             */
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
                Weather(it, getTempDesc(it))

            }
            _dailyForecast.value=forecastItems

        }


        private fun getTempDesc(temp: Float): String {
            return when (temp) {
                in Float.MIN_VALUE.rangeTo(0f) -> "Snowy"
                in 0f.rangeTo(50f) -> "Rainy"
                in 50f.rangeTo(75f) -> "Cloudy"
                in 75f.rangeTo(100f) -> "Sunny"
                else -> "Does not compute"
            }
        }
    }
