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


class MainActivity : ComponentActivity() {

    private val forecastRepository = ForecastRepository()
    private lateinit var tempDisplaySettingManager: TempDisplaySettingManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tempDisplaySettingManager = TempDisplaySettingManager(this)

        val zipcodeEditText : EditText = findViewById(R.id.zipcodeEditText)
        val enterButton : Button = findViewById(R.id.enterButton)

        enterButton.setOnClickListener {

            val zipcode : String = zipcodeEditText.text.toString()

            if(zipcode.length != 5){
                Toast.makeText(this, "Enter a valid zipcode number", Toast.LENGTH_SHORT).show()
            }
            else{
                forecastRepository.loadForecast(zipcode)
            }

        }

        val forecastListRecyclerView : RecyclerView = findViewById(R.id.forecastList_recyclerView)
        forecastListRecyclerView.layoutManager = LinearLayoutManager(this)


        val dailyAdapter = DailyAdapter(tempDisplaySettingManager){
            showForecastDetails(it)
        }
        forecastListRecyclerView.adapter = dailyAdapter

        val weeklyForecastObserver = Observer<List<Weather>>{forecastItems ->
            dailyAdapter.submitList(forecastItems)
        }
        forecastRepository.weeklyForecast.observe(this, weeklyForecastObserver)


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


    private fun showForecastDetails(forecast : Weather){
        val forecastDetailsIntent = Intent(this, WeatherDetailsActivity::class.java)
        forecastDetailsIntent.putExtra("key_temp", forecast.temp)
        forecastDetailsIntent.putExtra("key_description", forecast.description)
        startActivity(forecastDetailsIntent)
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

