package com.example.weatherapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.Adapter.DailyAdapter
import com.example.weatherapp.data.ForecastRepository
import com.example.weatherapp.data.Weather
import com.example.weatherapp.details.WeatherDetailsActivity
import com.example.weatherapp.forecasting.TodayWeatherFragment
import com.example.weatherapp.location.LocationSelectionFragment


class MainActivity : AppCompatActivity(), AppNavigator{


    private lateinit var tempDisplaySettingManager: TempDisplaySettingManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tempDisplaySettingManager = TempDisplaySettingManager(this)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragmentContainer, LocationSelectionFragment())
            .commit()

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
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, TodayWeatherFragment.newInstance(zipcode))
            .commit()
    }

    override fun navigateLocationSelection(){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, LocationSelectionFragment())
            .commit()

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

