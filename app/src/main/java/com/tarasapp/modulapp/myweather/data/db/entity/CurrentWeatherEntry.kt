package com.tarasapp.modulapp.myweather.data.db.entity


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

const val CURRENT_WEATHER_ID = 0

@Entity(tableName = "current_weather")
data class CurrentWeatherEntry(
//    val cloudcover: Int,
    val feelslike: Double,
    @SerializedName("is_day")
    val isDay: String,
    val precip: Int,
    val temperature: Int,
    @SerializedName("uv_index")
    val uvIndex: Int,
    val visibility: Int,
    @SerializedName("weather_code")
    val weatherCode: Int,
//    @SerializedName("weather_descriptions")
    var weatherDescription: String,
//    @SerializedName("weather_icons")
    var weatherIcon: String,
    @SerializedName("wind_dir")
    val windDir: String,
    @SerializedName("wind_speed")
    val windSpeed: Double
){
    @PrimaryKey(autoGenerate = false)
    var id: Int = CURRENT_WEATHER_ID
}