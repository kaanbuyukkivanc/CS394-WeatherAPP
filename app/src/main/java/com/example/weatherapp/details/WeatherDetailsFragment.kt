package com.example.weatherapp.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.weatherapp.R
import com.example.weatherapp.TempDisplaySettingManager
import com.example.weatherapp.databinding.FragmentWeatherDetailsBinding
import com.example.weatherapp.formatTemplateForDisplay
import com.example.weatherapp.showTempDisplayDialog

class WeatherDetailsFragment : Fragment() {

    private val args : WeatherDetailsFragmentArgs by navArgs()
    private val viewModel = WeatherDetailsViewModel()
    private var _binding : FragmentWeatherDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var tempDisplaySettingManager: TempDisplaySettingManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWeatherDetailsBinding.inflate(inflater, container, false)
        tempDisplaySettingManager = TempDisplaySettingManager(requireContext())

        binding.tempText.text = formatTemplateForDisplay(args.temp, tempDisplaySettingManager.getTimeDisplaySetting())
        binding.descriptionDetailsText.text = args.description


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewStateObserver = Observer<WeatherDetailsViewState> {
            viewState ->
            binding.tempText.text = formatTemplateForDisplay(viewState.temp, tempDisplaySettingManager.getTimeDisplaySetting())
            binding.descriptionDetailsText.text = viewState.description
            //binding.dateText = viewState.date
            //binding.icon.load(viewState.icon)
        }
        viewModel.viewState.observe(viewLifecycleOwner,viewStateObserver)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}


