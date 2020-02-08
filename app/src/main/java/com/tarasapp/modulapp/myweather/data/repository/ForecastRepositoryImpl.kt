package com.tarasapp.modulapp.myweather.data.repository

import androidx.lifecycle.LiveData
import com.tarasapp.modulapp.myweather.data.db.CurrentWeatherDao
import com.tarasapp.modulapp.myweather.data.db.entity.CurrentWeatherEntry
import com.tarasapp.modulapp.myweather.data.network.WeatherNetworkDataSource
import com.tarasapp.modulapp.myweather.data.network.response.CurrentWeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.ZonedDateTime
import java.util.*

class ForecastRepositoryImpl(
    private val currentWeatherDao: CurrentWeatherDao,
    private val weatherNetworkDataSource: WeatherNetworkDataSource
) : ForecastRepository {

    init {
        weatherNetworkDataSource.downloadedCurrentWeather.observeForever{newCurrentWeather ->
            persistFetchedCurrentWeather(newCurrentWeather)
        }
    }

    override suspend fun getCurrentWeather(): LiveData<out CurrentWeatherEntry> {
        initWeatherData()

        return withContext(Dispatchers.IO){
            return@withContext currentWeatherDao.getWeather()
        }
    }

    private fun persistFetchedCurrentWeather(fetchedWeather: CurrentWeatherResponse){
        GlobalScope.launch(Dispatchers.IO){
            currentWeatherDao.upsert(fetchedWeather.currentWeatherEntry)
        }
    }

    private suspend fun initWeatherData(){
        if(isFetchedCurrentNeeded(ZonedDateTime.now().minusHours(1)))
            fetchCurrentWeather()
    }

    private suspend fun fetchCurrentWeather(){
        weatherNetworkDataSource.fetchCurrnetWeather(
            "London",
            Locale.getDefault().language
        )
    }

    private fun isFetchedCurrentNeeded(lastFetchTime: ZonedDateTime): Boolean{
        val thirtyMinutesAgo = ZonedDateTime.now().minusMinutes(30)
        return lastFetchTime.isBefore(thirtyMinutesAgo)
    }
}