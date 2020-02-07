package com.tarasapp.modulapp.myweather.data.network.response

import com.google.gson.annotations.SerializedName
import com.tarasapp.modulapp.myweather.data.db.entity.CurrentWeatherEntry
import com.tarasapp.modulapp.myweather.data.db.entity.Location
import com.tarasapp.modulapp.myweather.data.db.entity.Request


data class CurrentWeatherResponse(
    @SerializedName("current")
    val currentWeatherEntry: CurrentWeatherEntry,
    val location: Location,
    val request: Request
)