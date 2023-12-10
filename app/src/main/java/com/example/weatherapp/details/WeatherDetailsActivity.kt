package com.example.weatherapp.details

import android.content.DialogInterface
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.TextView
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.weatherapp.R
import com.example.weatherapp.TempDisplaySetting
import com.example.weatherapp.TempDisplaySettingManager
import com.example.weatherapp.details.ui.theme.WeatherAppTheme
import com.example.weatherapp.formatTemplateForDisplay
import com.example.weatherapp.showTempDisplayDialog

class WeatherDetailsActivity : AppCompatActivity() {

    private lateinit var tempDisplaySettingManager: TempDisplaySettingManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_details)

        tempDisplaySettingManager = TempDisplaySettingManager(this)

        setTitle(R.string.forecast_details)

        val tempText : TextView = findViewById(R.id.tempText)
        val descriptionText : TextView = findViewById(R.id.descriptionDetailsText)

        val temp = intent.getFloatExtra("key_temp", 0f)

        tempText.text = formatTemplateForDisplay(temp, tempDisplaySettingManager.getTimeDisplaySetting())
        descriptionText.text = intent.getStringExtra("key_description")


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


}


