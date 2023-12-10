package com.example.weatherapp

import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AlertDialog


fun formatTemplateForDisplay(temp : Float, tempDisplaySettings : TempDisplaySetting) : String {
    return when (tempDisplaySettings){
        TempDisplaySetting.Fahrenheit -> String.format("%.2f F°",temp)
        TempDisplaySetting.Celcius ->{
            val temp = (temp - 32f) * (5f/9f)
            String.format("%.2f C°",temp)
        }
    }

}
public fun showTempDisplayDialog(context : Context, tempDisplaySettingManager: TempDisplaySettingManager){
    val dialogBuilder = AlertDialog.Builder(context)
    dialogBuilder.setTitle("Choose Display Units")
    dialogBuilder.setMessage("Choose the temperature unit")
    dialogBuilder.setPositiveButton("F"){_,_, ->
        tempDisplaySettingManager.updateSetting(TempDisplaySetting.Fahrenheit)
    }
    dialogBuilder.setNeutralButton("C"){_,_, ->
        tempDisplaySettingManager.updateSetting(TempDisplaySetting.Celcius)
    }
    dialogBuilder.setOnDismissListener{
        Toast.makeText(context,"Setting will take affect on app restart", Toast.LENGTH_SHORT).show()
    }
    dialogBuilder.show()
}