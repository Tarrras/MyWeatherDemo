package com.tarasapp.modulapp.myweather.data.db.entity

import androidx.room.TypeConverter

class DataConverter {

    @TypeConverter
    public fun fromList(myList: List<String>) = myList.joinToString()

    @TypeConverter
    fun toList(string: String) = listOf(string)
}