package com.tarasapp.modulapp.myweather.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tarasapp.modulapp.myweather.data.ApixuWeatherApiService
import com.tarasapp.modulapp.myweather.data.network.response.CurrentWeatherResponse
import com.tarasapp.modulapp.myweather.internal.NoConnectivityException

class WeatherNetworkDataSourceImpl(
    private val apixuWeatherApiService: ApixuWeatherApiService
) : WeatherNetworkDataSource {

    private val _downloadedCurrentWeather = MutableLiveData<CurrentWeatherResponse>()
    override val downloadedCurrentWeather: LiveData<CurrentWeatherResponse>
        get() = _downloadedCurrentWeather

    override suspend fun fetchCurrnetWeather(location: String, languageCode: String) {
        try {
            val fetchCurrentWeather = apixuWeatherApiService
                .getCurrentWeather(location, languageCode)
            _downloadedCurrentWeather.postValue(fetchCurrentWeather)
        } catch (ex: Exception) {
            Log.e("connectivity", "No internet")
        }
    }
}