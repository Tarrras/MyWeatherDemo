package com.tarasapp.modulapp.myweather.UI.weather.current

import android.graphics.Color
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

import com.tarasapp.modulapp.myweather.R
import com.tarasapp.modulapp.myweather.UI.base.ScopedFragment
import com.tarasapp.modulapp.myweather.data.ApixuWeatherApiService
import com.tarasapp.modulapp.myweather.data.network.ConnectivityInterceptorImpl
import com.tarasapp.modulapp.myweather.data.network.WeatherNetworkDataSourceImpl
import kotlinx.android.synthetic.main.current_weather_fragment.*
import kotlinx.android.synthetic.main.current_weather_fragment.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class CurrentWeatherFragment : ScopedFragment(), KodeinAware {
    private val viewModelFactory: CurrentWeatherViewModelFactory by instance()


    private lateinit var viewModel: CurrentWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.current_weather_fragment, container, false)
//        swipeRefreshLayout = view.swipe_item
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(CurrentWeatherViewModel::class.java)
        bindUi()
        swipe_item.setColorSchemeColors(
            Color.RED, Color.GREEN, Color.BLUE, Color.CYAN
        )

        swipe_item.setOnRefreshListener {
            updateData()
        }
    }

    private fun updateData() {
        viewModel.refreshOnSwipe()
    }

    override val kodein by closestKodein()

    private fun bindUi() = GlobalScope.launch(Dispatchers.Main){
//        val currentWeather = viewModel.weatherData.await()
//        currentWeather.observe(this@CurrentWeatherFragment, Observer {
//            if(it == null) return@Observer
//
//            textView.text = it.toString()
//        })
        viewModel.data.observe(this@CurrentWeatherFragment, Observer {
            if(it == null) return@Observer
            textView.text = it.toString()
        })
        viewModel.refreshing.observe(this@CurrentWeatherFragment , Observer {
                swipe -> swipe_item.isRefreshing = swipe
        })
    }



}
