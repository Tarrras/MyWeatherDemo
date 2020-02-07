package com.tarasapp.modulapp.myweather.data.network

import androidx.lifecycle.LiveData
import com.tarasapp.modulapp.myweather.data.db.entity.CurrentWeatherEntry
import com.tarasapp.modulapp.myweather.data.network.response.CurrentWeatherResponse

interface WeatherNetworkDataSource {
    val downloadedCurrentWeather: LiveData<CurrentWeatherResponse>

    suspend fun fetchCurrnetWeather(
        location: String,
        languageCode: String
    )
}