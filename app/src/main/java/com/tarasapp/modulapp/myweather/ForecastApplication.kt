package com.tarasapp.modulapp.myweather

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.tarasapp.modulapp.myweather.UI.weather.current.CurrentWeatherViewModelFactory
import com.tarasapp.modulapp.myweather.data.ApixuWeatherApiService
import com.tarasapp.modulapp.myweather.data.db.ForecastDatabase
import com.tarasapp.modulapp.myweather.data.network.ConnectivityInterceptor
import com.tarasapp.modulapp.myweather.data.network.ConnectivityInterceptorImpl
import com.tarasapp.modulapp.myweather.data.network.WeatherNetworkDataSource
import com.tarasapp.modulapp.myweather.data.network.WeatherNetworkDataSourceImpl
import com.tarasapp.modulapp.myweather.data.repository.ForecastRepository
import com.tarasapp.modulapp.myweather.data.repository.ForecastRepositoryImpl
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.androidModule
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class ForecastApplication: Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@ForecastApplication))

        bind() from singleton { ForecastDatabase(instance()) }
        bind() from singleton { instance<ForecastDatabase>().currentWeatherDao() }

        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { ApixuWeatherApiService(instance()) }

        bind<WeatherNetworkDataSource>() with singleton { WeatherNetworkDataSourceImpl(instance()) }
        bind<ForecastRepository>() with singleton { ForecastRepositoryImpl(instance(), instance()) }
        bind() from provider { CurrentWeatherViewModelFactory(instance()) }
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }

}