package com.bogdandev.weatherstationapp.app

import kotlinx.serialization.Serializable


@Serializable
data class WeatherInfo(
    val pressure:Double = 0.0,
    val humidity: Double = 0.0,
    val temperature: Double = 0.0,
) {
    fun toImperial(): WeatherInfo {
        return WeatherInfo(
            pressure = kPaToAtm(pressure),
            humidity = humidity,
            temperature = celsiusToFahrenheit(temperature)
        )

    }
    fun toSI() : WeatherInfo {
        return this
    }
    fun kPaToAtm(pressure: Double):Double{
        return pressure* 0.00986923267
    }
    fun celsiusToFahrenheit(temperature: Double): Double{
        return temperature * 1.8 + 32
    }
}

