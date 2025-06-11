package com.bogdandev.weatherstationapp.app

import androidx.compose.runtime.getValue

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class WeatherStationViewModel: ViewModel() {
      var weatherInfo by mutableStateOf(WeatherInfo())
}