package com.example.weatherapp.location

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.R
import com.example.weatherapp.data.ForecastRepository
import com.example.weatherapp.forecasting.TodayWeatherFragment

class LocationSelectionFragment : Fragment() {

    private val forecastRepository = ForecastRepository()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_location_selection, container, false)

        val zipcodeEditText : EditText = view.findViewById(R.id.zipcodeEditText)
        val enterButton : Button = view.findViewById(R.id.enterButton)

        enterButton.setOnClickListener {

            val zipcode : String = zipcodeEditText.text.toString()

            if(zipcode.length != 5){
                Toast.makeText(requireContext(), "Enter a valid zipcode number", Toast.LENGTH_SHORT).show()
            }
            else{
                forecastRepository.loadForecastDaily(zipcode)
                forecastRepository.loadForecastWeekly(zipcode)
                navigateToWeatherFragment(zipcode)

            }

        }

        return view
    }
    private fun navigateToWeatherFragment(zipcode: String) {
        val action = LocationSelectionFragmentDirections.actionLocationSelectionFragmentToTodayWeatherFragment2(zipcode)
        findNavController().navigate(action)
    }
    companion object{
        const val ZIPCODE_KEY = "zipcode_key"
        fun newInstance(zipcode:String) : LocationSelectionFragment {
            val fragment = LocationSelectionFragment()

            val args = Bundle()
            args.putString(ZIPCODE_KEY, zipcode)
            fragment.arguments = args

            return fragment
        }

    }


    }
