package com.example.weatherapp.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.weatherapp.R
import com.example.weatherapp.TempDisplaySettingManager
import com.example.weatherapp.data.Weather
import com.example.weatherapp.databinding.FragmentWeatherDetailsBinding
import com.example.weatherapp.formatTemplateForDisplay

class WeatherDetailsFragment : Fragment() {

    private val args : WeatherDetailsFragmentArgs by navArgs()
    private val viewModel = WeatherDetailsViewModel()
    private lateinit var tempDisplaySettingManager: TempDisplaySettingManager

    private lateinit var binding: FragmentWeatherDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_weather_details, container, false)
        tempDisplaySettingManager = TempDisplaySettingManager(requireContext())

        binding.weather = getWeatherFromArgs()
        binding.tempDetails.setText(formatTemplateForDisplay(args.temp, tempDisplaySettingManager.getTimeDisplaySetting()))

        return binding.root
    }
    private fun getWeatherFromArgs(): Weather {
        return Weather(args.temp,args.description,args.date,args.icon) // Assuming 'weather' is the correct property in your args class
    }

}


