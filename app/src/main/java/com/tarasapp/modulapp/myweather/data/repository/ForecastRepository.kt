package com.tarasapp.modulapp.myweather.data.repository

import androidx.lifecycle.LiveData
import com.tarasapp.modulapp.myweather.data.db.entity.CurrentWeatherEntry

interface ForecastRepository {
    suspend fun cacheWeather()
    fun getCurrentWeather(): LiveData<CurrentWeatherEntry>
}