package com.tarasapp.modulapp.myweather.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tarasapp.modulapp.myweather.data.db.entity.CURRENT_WEATHER_ID
import com.tarasapp.modulapp.myweather.data.db.entity.CurrentWeatherEntry
import com.tarasapp.modulapp.myweather.data.db.unitlocalize.CurrentWeatherEntryDb

@Dao
interface CurrentWeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(weatherEntry: CurrentWeatherEntry)

    @Query("select * from current_weather where id=$CURRENT_WEATHER_ID")
    fun getWeather(): LiveData<CurrentWeatherEntry>
}