package com.bogdandev.weatherstationapp.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

const val REQUEST_INTERVAL_MS:Long  =  30_000L
const val URL: String =  "http://192.168.1.1:80/get"; // TODO url selector

class WeatherStationViewModel : ViewModel() {
    private var isActive = true
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
            delay(REQUEST_INTERVAL_MS)
        }
    }
}