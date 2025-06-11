package com.bogdandev.weatherstationapp.app

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.client.HttpClient
import io.ktor.client.statement.HttpResponse
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get
import io.ktor.client.request.request
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

const val REQUEST_INTERVAL_MS:Long  =  30_000L
const val URL: String =  "http://192.168.1.1:80/" // TODO url selector

class WeatherStationViewModel : ViewModel() {
    private var isActive = true
    private val client = HttpClient(CIO)
    private var _weatherInfo = MutableStateFlow(WeatherInfo())
    var weatherInfo = _weatherInfo.asStateFlow()


    init {
        viewModelScope.launch {
           fetchWeatherInfo()
        }
    }

    private suspend fun fetchWeatherInfo() {

        while (isActive) {
            _weatherInfo.value = WeatherInfo()  //todo
            val response: HttpResponse = client.get("https://ktor.io/docs/welcome.html")
            Log.i("fetchWeatherInfo",response.toString())
            delay(REQUEST_INTERVAL_MS)
        }
    }
}