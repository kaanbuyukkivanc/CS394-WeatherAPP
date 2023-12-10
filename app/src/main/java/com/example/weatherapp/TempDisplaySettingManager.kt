package com.example.weatherapp

import android.content.Context

enum class TempDisplaySetting{
    Fahrenheit,Celcius
}

class TempDisplaySettingManager(context: Context) {
    private val preference = context.getSharedPreferences("settings", Context.MODE_PRIVATE)

    fun updateSetting(setting: TempDisplaySetting){
        preference.edit().putString("key_temp_display", setting.name).commit()
    }

    fun getTimeDisplaySetting() : TempDisplaySetting{
        val settingValue = preference.getString("key_temp_display", TempDisplaySetting.Fahrenheit.name) ?: TempDisplaySetting.Fahrenheit.name
        return TempDisplaySetting.valueOf(settingValue)
    }

}