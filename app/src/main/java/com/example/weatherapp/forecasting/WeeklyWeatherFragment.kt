
package com.example.weatherapp.forecasting

import ForecastViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.Adapter.DailyAdapter
import com.example.weatherapp.R
import com.example.weatherapp.TempDisplaySettingManager
import com.example.weatherapp.data.Weather
import com.google.android.material.floatingactionbutton.FloatingActionButton

class WeeklyWeatherFragment : Fragment() {
    private val viewModel: ForecastViewModel by activityViewModels()
    private lateinit var tempDisplaySettingManager: TempDisplaySettingManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_weekly_weather, container, false)
        tempDisplaySettingManager = TempDisplaySettingManager(requireContext())

        val forecastListRecyclerView: RecyclerView = view.findViewById(R.id.forecastList_recyclerView)
        forecastListRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        val dailyAdapter = DailyAdapter(tempDisplaySettingManager) { showForecastDetails(it) }
        forecastListRecyclerView.adapter = dailyAdapter

        val weeklyForecastObserver = Observer<List<Weather>> { forecastItems ->
            dailyAdapter.submitList(forecastItems)
        }
        viewModel.weeklyForecast.observe(viewLifecycleOwner, weeklyForecastObserver)

        val locationButton: FloatingActionButton = view.findViewById(R.id.floatingActionButton2)
        locationButton.setOnClickListener {
            showLocationSelection()
        }


        return view
    }

    private fun showLocationSelection() {
        val action = WeeklyWeatherFragmentDirections.actionWeeklyWeatherFragmentToLocationSelectionFragment()
        findNavController().navigate(action)
    }

    private fun showForecastDetails(forecast: Weather) {
        val action =
            WeeklyWeatherFragmentDirections.actionWeeklyWeatherFragmentToWeatherDetailsFragment(
                forecast.temp, forecast.description, forecast.date, forecast.icon
            )
        findNavController().navigate(action)
    }


}
