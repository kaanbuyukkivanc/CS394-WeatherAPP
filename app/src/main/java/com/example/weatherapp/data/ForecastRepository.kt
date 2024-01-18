import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.R
import com.example.weatherapp.data.Weather
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.Date

data class WeatherForecast(
    val temperature: Float = 0.0f,
    val cityName: String = "",
    val date: String = "",
    val description: String = ""
)

class ForecastRepository {

    private val _weeklyForecast = MutableLiveData<List<Weather>>()
    val weeklyForecast: LiveData<List<Weather>> = _weeklyForecast

    private val _dailyForecast = MutableLiveData<List<Weather>>()
    val dailyForecast: LiveData<List<Weather>> = _dailyForecast

    private val dateFormat = SimpleDateFormat("dd-MM-yyyy")

    private val db = FirebaseFirestore.getInstance()

    @RequiresApi(Build.VERSION_CODES.O)
    fun loadForecastWeekly(zipcode: String) {
        val weatherCollection = db.collection("weather_forecasts")
            .document(zipcode)
            .collection("weekly_forecast")

        weatherCollection.get().addOnSuccessListener { documents ->
            val forecastItems = documents.documents.mapNotNull { document ->
                document.toObject(WeatherForecast::class.java)?.let {
                    Weather(it.temperature, it.description, it.date, updateUI(it.description))
                }
            }
            _weeklyForecast.postValue(forecastItems)
        }.addOnFailureListener { e ->
            println("loadForecastWeekly failed")
        }
    }

    fun loadForecastDaily(zipcode: String) {
        val weatherCollection = db.collection("weather_forecasts")
            .document(zipcode)
            .collection("weekly_forecast")

        weatherCollection.limit(1).get().addOnSuccessListener { documents ->
            val forecastItems = documents.documents.mapNotNull { document ->
                document.toObject(WeatherForecast::class.java)?.let {
                    Weather(it.temperature, it.description, it.date, updateUI(it.description))
                }
            }
            _dailyForecast.postValue(forecastItems)
        }.addOnFailureListener { e ->
            println("loadForecastWeekly failed")
        }
    }

    private fun updateUI(description: String): Int {
        return when (description) {
            "Snowy" -> R.drawable.snow
            "Thunder" -> R.drawable.thunder
            "Rainy" -> R.drawable.rain
            "Foggy" -> R.drawable.fog
            "Cloudy" -> R.drawable.cloud
            "Sunny" -> R.drawable.sun
            else -> 0
        }
    }
}
