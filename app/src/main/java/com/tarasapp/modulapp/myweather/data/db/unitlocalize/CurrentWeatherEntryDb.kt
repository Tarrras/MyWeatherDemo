package com.tarasapp.modulapp.myweather.data.db.unitlocalize

import androidx.room.ColumnInfo

data class CurrentWeatherEntryDb (
    @ColumnInfo(name = "feelslike")
    val feelsLike: Double,
    @ColumnInfo(name = "is_day")
    val isDay: String,
    @ColumnInfo(name = "precip")
    val precipitation: Int,
    @ColumnInfo(name = "temperature")
    val temperature: Int,
    @ColumnInfo(name = "uv_index")
    val uvIndex: Int,
    @ColumnInfo(name = "visibility")
    val visibility: Int,
    @ColumnInfo(name = "weather_code")
    val weatherCode: Int,
    @ColumnInfo(name = "weather_descriptions")
    val weatherDescriptions: List<String>,
    @ColumnInfo(name = "weather_icons")
    val weatherIcons: List<String>,
    @ColumnInfo(name = "wind_dir")
    val windDir: String,
    @ColumnInfo(name = "wind_speed")
    val windSpeed: Double
)