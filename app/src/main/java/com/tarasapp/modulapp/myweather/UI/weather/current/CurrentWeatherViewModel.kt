package com.tarasapp.modulapp.myweather.UI.weather.current

import androidx.lifecycle.ViewModel
import com.tarasapp.modulapp.myweather.data.repository.ForecastRepository
import com.tarasapp.modulapp.myweather.internal.lazyDeffered

class CurrentWeatherViewModel(
    private val forecastRepository: ForecastRepository
) : ViewModel() {

    val isMetric: Boolean
        get() = true

    val weather by lazyDeffered {
        forecastRepository.getCurrentWeather()
    }


}
