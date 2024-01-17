package com.example.weatherapp.location

import ForecastViewModel
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.R



class LocationSelectionFragment : Fragment() {

    private val forecastViewModel: ForecastViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_location_selection, container, false)


        val enterButton : Button = view.findViewById(R.id.enterButton)

        enterButton.setOnClickListener {
            val zipcodeEditText : EditText = view.findViewById(R.id.zipcodeEditText)


            val zipcode : String = zipcodeEditText.text.toString()


            if(zipcode.length != 5){
                Toast.makeText(requireContext(), "Enter a valid zipcode number", Toast.LENGTH_SHORT).show()
            }
            else{

                forecastViewModel.loadDailyForecast(zipcode)
                forecastViewModel.loadWeeklyForecast(zipcode)
                navigateToWeatherFragment(zipcode)

            }

        }

        return view
    }
    private fun navigateToWeatherFragment(zipcode: String) {
        val action = LocationSelectionFragmentDirections.actionLocationSelectionFragmentToTodayWeatherFragment2(zipcode)
        findNavController().navigate(action)
    }



}