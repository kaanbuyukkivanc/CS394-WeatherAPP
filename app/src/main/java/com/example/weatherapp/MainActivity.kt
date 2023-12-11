package com.example.weatherapp

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import com.example.weatherapp.data.Weather
import com.example.weatherapp.forecasting.TodayWeatherFragmentDirections
import androidx.navigation.ui.setupWithNavController
import com.example.weatherapp.location.LocationSelectionFragmentDirections


class MainActivity : AppCompatActivity(), AppNavigator{


    private lateinit var tempDisplaySettingManager: TempDisplaySettingManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tempDisplaySettingManager = TempDisplaySettingManager(this)

        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfig = AppBarConfiguration(navController.graph)

        findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar).setupWithNavController(navController,appBarConfig)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater : MenuInflater = menuInflater
        inflater.inflate(R.menu.settings_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {

            R.id.tempDisplaySetting -> {
                showTempDisplayDialog(this, tempDisplaySettingManager)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun navigateWeatherOfToday(zipcode: String) {
        val action = LocationSelectionFragmentDirections.actionLocationSelectionFragmentToTodayWeatherFragment()
        findNavController(R.id.nav_host_fragment).navigate(action)
    }

    override fun navigateLocationSelection(){
        val action = TodayWeatherFragmentDirections.actionTodayWeatherFragmentToLocationSelectionFragment()
        findNavController(R.id.nav_host_fragment).navigate(action)

    }

    override fun navigateToDetails(forecast: Weather) {
        val action = TodayWeatherFragmentDirections.actionTodayWeatherFragmentToWeatherDetailsFragment(forecast.temp,forecast.description)
        findNavController(R.id.nav_host_fragment).navigate(action)
    }


    override fun onStart(){
        super.onStart()
    }

    override fun onResume(){
        super.onResume()
    }

    override fun onPause(){
        super.onPause()
    }

    override fun onStop(){
        super.onStop()
    }

    override fun onDestroy(){
        super.onDestroy()
    }


}

