import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.data.ForecastRepository
import com.example.weatherapp.data.Weather

class ForecastViewModel : ViewModel() {

    private val forecastRepository = ForecastRepository()

    private val _weeklyForecast: MutableLiveData<List<Weather>> = MutableLiveData()
    val weeklyForecast: LiveData<List<Weather>> get() = _weeklyForecast

    private val _dailyForecast: MutableLiveData<List<Weather>> = MutableLiveData()
    val dailyForecast: LiveData<List<Weather>> get() = _dailyForecast

    init {
        forecastRepository.weeklyForecast.observeForever { forecastItems ->
            _weeklyForecast.value = forecastItems
        }

        forecastRepository.dailyForecast.observeForever { dailyForecastItem ->
            _dailyForecast.value = dailyForecastItem
        }
    }

    @SuppressLint("NewApi")
    fun loadWeeklyForecast(zipcode: String) {
        forecastRepository.loadForecastWeekly(zipcode)
    }

    @SuppressLint("NewApi")
    fun loadDailyForecast(zipcode: String) {
        forecastRepository.loadForecastDaily(zipcode)
    }

    override fun onCleared() {
        forecastRepository.weeklyForecast.removeObserver { }
        forecastRepository.dailyForecast.removeObserver { }
        super.onCleared()
    }
}