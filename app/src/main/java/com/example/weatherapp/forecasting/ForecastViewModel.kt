
package com.example.weatherapp.forecasting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.data.ForecastRepository
import com.example.weatherapp.data.Weather

class ForecastViewModel : ViewModel() {

    private val forecastRepository = ForecastRepository()
    private val _weeklyForecast: MutableLiveData<List<Weather>> = MutableLiveData()
    val weeklyForecast: LiveData<List<Weather>> get() = _weeklyForecast

    init {
        forecastRepository.weeklyForecast.observeForever { forecastItems ->
            _weeklyForecast.value = forecastItems
        }
    }

    fun loadForecast(cityName: String) {
        forecastRepository.loadForecast(cityName)
    }

    override fun onCleared() {
        forecastRepository.weeklyForecast.removeObserver { }
        super.onCleared()
    }
}
