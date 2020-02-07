package com.tarasapp.modulapp.myweather.data.db.entity


import com.google.gson.*
import com.google.gson.reflect.TypeToken

import java.lang.reflect.Type
import java.util.ArrayList

class MyDeserializer : JsonDeserializer<CurrentWeatherEntry> {

    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): CurrentWeatherEntry {
        val decodeObj = json.asJsonObject
        val gson = Gson()
        val decode = gson.fromJson(json, CurrentWeatherEntry::class.java)
        var values = String()
        var values1 = String()

        if (decodeObj.get("weather_icons").isJsonArray) {
            val temp = gson.fromJson<ArrayList<String>>(
                decodeObj.get("weather_icons"),
                object : TypeToken<List<String>>() {
                }.type
            )
            values = temp.joinToString()

        }
        if (decodeObj.get("weather_descriptions").isJsonArray) {
            val temp = gson.fromJson<ArrayList<String>>(
                decodeObj.get("weather_descriptions"),
                object : TypeToken<List<String>>() {
                }.type
            )
            values1 = temp.joinToString()
        }

        decode.weatherIcon = values
        decode.weatherDescription = values1
        return decode
    }
}
