package com.example.weatherapp

import com.example.weatherapp.data.Weather

interface AppNavigator {

    fun navigateWeatherOfToday(zipcode:String)
    fun navigateLocationSelection()
    fun navigateToDetails(forecast:Weather)
}