package com.bogdandev.weatherstationapp.app

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.network.sockets.ConnectTimeoutException
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.plugins.timeout
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.net.ConnectException
import java.net.SocketException

const val TIMEOUT_MS: Long = 1000L
const val REQUEST_INTERVAL_MS: Long = 100L
const val URL: String = "http://192.168.1.1/" // TODO url selector

class WeatherStationViewModel : ViewModel() {
    private var isActive = true
    private val client = HttpClient(CIO)
    private var _weatherInfo = MutableStateFlow(WeatherInfo())
    var weatherInfo = _weatherInfo.asStateFlow()
    private var _isConnected = MutableStateFlow(false)
    var isConnected = _isConnected.asStateFlow()

    init {
        viewModelScope.launch {
            fetchWeatherInfo(URL)
        }
    }

    private suspend fun fetchWeatherInfo(url: String) {
        _weatherInfo.value = WeatherInfo()
        _isConnected.value = false
        var isConnected = false
        while (isActive) {
            try {
                val response = client.get(url) {
                    timeout {
                        requestTimeoutMillis = TIMEOUT_MS
                    }

                }
                val responseBody = response.bodyAsText()
                if (!responseBody.isEmpty()){
                    isConnected = true
                }
                Log.d("fetchWeatherInfo response", response.toString())
                Log.d("fetchWeatherInfo content", responseBody)
                try {

                    this._weatherInfo.value = Json.decodeFromString<WeatherInfo>(responseBody)
                    Log.d("fetchWeatherInfo JSON", this._weatherInfo.value.toString())

                } catch (e: SerializationException) {
                    Log.w("fetchWeatherInfo json", e.toString())

                } catch (e: IllegalArgumentException) {
                    Log.w("fetchWeatherInfo json", e.toString())
                }
                catch (e: NullPointerException) {
                Log.w("fetchWeatherInfo json", e.toString())
            }
            } catch (e: ConnectException) {
                Log.i("fetchWeatherInfo", "Connect the freaking station")
                Log.e("fetchWeatherInfo", e.toString())
            } catch (e: ConnectTimeoutException) {
                Log.i("fetchWeatherInfo", "Connect the freaking station")
                Log.e("fetchWeatherInfo", e.toString())
                isConnected = false
            } catch (e: SocketTimeoutException) {
                Log.i("fetchWeatherInfo", "Connect the freaking wifi!!!")
                Log.e("fetchWeatherInfo", e.toString())
                isConnected = false
            } catch (e: SocketException) {
                Log.i("fetchWeatherInfo", "Connect the freaking station!!!")
                Log.e("fetchWeatherInfo", e.toString())
                isConnected = false
            } catch (e: HttpRequestTimeoutException) {
                Log.i("fetchWeatherInfo", "Slow slime!!!")
                Log.e("fetchWeatherInfo", e.toString())
                isConnected= false
            } catch (e: Exception) {
                Log.wtf("fetchWeatherInfo", "I don't know !!!")
                Log.e("fetchWeatherInfo", e.toString())
                isConnected= false
            } finally {
                delay(REQUEST_INTERVAL_MS)
               _isConnected.value = isConnected
            }
        }
    }
}