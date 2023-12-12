package com.example.weatherapp.details

data class WeatherDetailsViewState(
    val temp: Float,
    val description: String,
    val date: String,
    val icon: Int
)