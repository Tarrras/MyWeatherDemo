package com.tarasapp.modulapp.myweather.UI.weather.current

import android.text.BoringLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tarasapp.modulapp.myweather.data.db.entity.CurrentWeatherEntry
import com.tarasapp.modulapp.myweather.data.repository.ForecastRepository
import com.tarasapp.modulapp.myweather.internal.lazyDeffered
import kotlinx.coroutines.*

class CurrentWeatherViewModel(
    private val forecastRepository: ForecastRepository
) : ViewModel() {

//    fun loadData() {
//        viewModelScope.launch {
//            forecastRepository.cacheWeather()
//        }
//    }

//    init {
//        viewModelScope.launch {
//            forecastRepository.cacheWeather()
//        }
//    }

    val weatherData by lazyDeffered {
        forecastRepository.getCurrentWeather()
    }

    private fun <T> lazyDeffered(block: suspend CoroutineScope.() -> T): Lazy<Deferred<T>>{
        return lazy {
            GlobalScope.async( start = CoroutineStart.LAZY) {
                block.invoke(this)
            }
        }
    }

    val data: LiveData<CurrentWeatherEntry> = forecastRepository.getCurrentWeather()

    private val _refreshing = MutableLiveData<Boolean>(false)
    val refreshing: LiveData<Boolean>
        get() = _refreshing

    fun refreshOnSwipe(){
        viewModelScope.launch {
            try {
                _refreshing.postValue(true)
                forecastRepository.getCurrentWeather()
            } finally {
                _refreshing.postValue(false)
            }
        }

    }

}
