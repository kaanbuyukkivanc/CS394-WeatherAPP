package com.example.weatherapp.forecasting

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.FloatingActionButton
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.Adapter.DailyAdapter
import com.example.weatherapp.AppNavigator
import com.example.weatherapp.R
import com.example.weatherapp.TempDisplaySettingManager
import com.example.weatherapp.data.ForecastRepository
import com.example.weatherapp.data.Weather
import com.example.weatherapp.details.WeatherDetailsActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TodayWeatherFragment : Fragment() {

    private lateinit var tempDisplaySettingManager: TempDisplaySettingManager
    private val forecastRepository = ForecastRepository()

    private lateinit var appNavigator: AppNavigator

    override fun onAttach(context: Context) {
        super.onAttach(context)
        appNavigator = context as AppNavigator
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        tempDisplaySettingManager = TempDisplaySettingManager(requireContext())
        val zipcode = requireArguments().getString(ZIPCODE_KEY) ?: ""
        val view = inflater.inflate(R.layout.fragment_today_weather, container, false)

        val locationButton : FloatingActionButton = view.findViewById(R.id.floatingActionButton2)
        locationButton.setOnClickListener{
            appNavigator.navigateLocationSelection()
        }

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
        forecastRepository.loadForecast(zipcode)
        return view
    }

    private fun showForecastDetails(forecast : Weather){
        val forecastDetailsIntent = Intent(requireContext(), WeatherDetailsActivity::class.java)
        forecastDetailsIntent.putExtra("key_temp", forecast.temp)
        forecastDetailsIntent.putExtra("key_description", forecast.description)
        startActivity(forecastDetailsIntent)
    }

    companion object{
        const val ZIPCODE_KEY = "zipcode_key"
        fun newInstance(zipcode:String) : TodayWeatherFragment{
            val fragment = TodayWeatherFragment()

            val args = Bundle()
            args.putString(ZIPCODE_KEY, zipcode)
            fragment.arguments = args

            return fragment
        }

    }


}