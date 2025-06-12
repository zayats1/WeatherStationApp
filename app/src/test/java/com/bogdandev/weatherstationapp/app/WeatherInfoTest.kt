package com.bogdandev.weatherstationapp.app

import org.junit.Assert.assertEquals
import org.junit.Test

const val TOLERANCE: Double = 1e-5

class WeatherInfoTest {
    private val weatherInfo = WeatherInfo(
        pressure = 101.325,
        humidity = 0.80,
        temperature = 20.0
    )

    @Test
    fun toImperial() {
        val infoInImperial = weatherInfo.toImperial()
        assertEquals(1.0 + 2.9e-10, infoInImperial.pressure, TOLERANCE)
        assertEquals(68.0, infoInImperial.temperature, TOLERANCE)
        assertEquals(weatherInfo.humidity, infoInImperial.humidity, TOLERANCE)
    }

    @Test
    fun toSI() {
        val infoInSI = weatherInfo.toSI()
        assertEquals(weatherInfo.pressure, infoInSI.pressure, TOLERANCE)
        assertEquals(weatherInfo.humidity, infoInSI.humidity, TOLERANCE)
        assertEquals(weatherInfo.temperature, infoInSI.temperature, TOLERANCE)
    }

    @Test
    fun kPaToAtm() {
        val atm = weatherInfo.kPaToAtm(weatherInfo.pressure)
        assertEquals(1.0 + 2.9e-10, atm, TOLERANCE)
    }

    @Test
    fun celsiusToFahrenheit() {
        val t = weatherInfo.celsiusToFahrenheit(weatherInfo.temperature)
        assertEquals(68.0, t, TOLERANCE)
    }
}