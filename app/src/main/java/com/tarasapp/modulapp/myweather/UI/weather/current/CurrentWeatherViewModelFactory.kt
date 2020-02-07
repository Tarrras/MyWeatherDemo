package com.tarasapp.modulapp.myweather.UI.weather.current

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tarasapp.modulapp.myweather.data.repository.ForecastRepository

@Suppress("UNCHECKED_CAST")
class CurrentWeatherViewModelFactory(
    private val forecastRepository: ForecastRepository
): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CurrentWeatherViewModel(forecastRepository) as T
    }
}