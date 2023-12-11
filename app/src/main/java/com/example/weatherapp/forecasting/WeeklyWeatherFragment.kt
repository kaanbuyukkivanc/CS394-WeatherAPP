package com.example.weatherapp.forecasting

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.Adapter.DailyAdapter
import com.example.weatherapp.AppNavigator
import com.example.weatherapp.R
import com.example.weatherapp.TempDisplaySettingManager
import com.example.weatherapp.data.ForecastRepository
import com.example.weatherapp.data.Weather
import com.example.weatherapp.details.WeatherDetailsFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class WeeklyWeatherFragment : Fragment() {

    private val forecastRepository = ForecastRepository()
    private lateinit var tempDisplaySettingManager: TempDisplaySettingManager
    private lateinit var appNavigator: AppNavigator

    override fun onAttach(context: Context) {
        super.onAttach(context)
        appNavigator = context as AppNavigator
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view = inflater.inflate(R.layout.fragment_weekly_weather, container, false)
        val zipcode = requireArguments().getString(ZIPCODE_KEY) ?: ""
        tempDisplaySettingManager = TempDisplaySettingManager(requireContext())


        val forecastListRecyclerView : RecyclerView = view.findViewById(R.id.forecastList_recyclerView)
        forecastListRecyclerView.layoutManager = LinearLayoutManager(requireContext())


        val dailyAdapter = DailyAdapter(tempDisplaySettingManager){
            showForecastDetails(it)
        }
        forecastListRecyclerView.adapter = dailyAdapter

        val weeklyForecastObserver = Observer<List<Weather>>{ forecastItems ->
            dailyAdapter.submitList(forecastItems)
        }
        forecastRepository.weeklyForecast.observe(viewLifecycleOwner, weeklyForecastObserver)

        val locationButton : FloatingActionButton = view.findViewById(R.id.floatingActionButton2)
        locationButton.setOnClickListener{
            appNavigator.navigateLocationSelection()
        }

        forecastRepository.loadForecast(zipcode)
        return view
    }

    private fun showForecastDetails(forecast : Weather){
    appNavigator.navigateToDetails(forecast)
    }

    companion object{
        const val ZIPCODE_KEY = "zipcode_key"
        fun newInstance(zipcode:String) : WeeklyWeatherFragment{
            val fragment = WeeklyWeatherFragment()

            val args = Bundle()
            args.putString(ZIPCODE_KEY, zipcode)
            fragment.arguments = args

            return fragment
        }

    }


}