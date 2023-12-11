package com.example.weatherapp.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WeatherDetailsViewModel : ViewModel() {
    private val _viewState: MutableLiveData<WeatherDetailsViewState> = MutableLiveData()
    val viewState: LiveData<WeatherDetailsViewState> = _viewState

    fun processArgs(args:WeatherDetailsFragmentArgs){
        if(_viewState.value != null) return
        _viewState.value = WeatherDetailsViewState(
            temp = args.temp,
            description = args.description
        )
    }
}