package com.tarasapp.modulapp.myweather.data

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.tarasapp.modulapp.myweather.data.db.entity.CurrentWeatherEntry
import com.tarasapp.modulapp.myweather.data.db.entity.MyDeserializer
import com.tarasapp.modulapp.myweather.data.network.ConnectivityInterceptor
import com.tarasapp.modulapp.myweather.data.network.response.CurrentWeatherResponse
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "36eff393ed28d44a02a822867671dd61"

//http://api.weatherstack.com/current?access_key=36eff393ed28d44a02a822867671dd61&query=New%20York

interface ApixuWeatherApiService {
    @GET("/current")
    fun getCurrentWeather(
        @Query("query") location: String = "London",
        @Query("len") languageCode: String = "en"
    ): Deferred<CurrentWeatherResponse>

    companion object{
        operator fun invoke(
            connectivityInterceptor: ConnectivityInterceptor
        ): ApixuWeatherApiService{
            val requestInterceptor = Interceptor{chain ->
                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("access_key", API_KEY)
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

                return@Interceptor chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addInterceptor(connectivityInterceptor)
                .build()

            val gson =GsonBuilder()
                .setLenient()
                .registerTypeAdapter(CurrentWeatherEntry::class.java, MyDeserializer())
                .create()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://api.weatherstack.com")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(ApixuWeatherApiService::class.java)
        }
    }
}