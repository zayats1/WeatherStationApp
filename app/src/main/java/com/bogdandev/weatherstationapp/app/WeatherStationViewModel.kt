package com.bogdandev.weatherstationapp.app

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.client.HttpClient
import io.ktor.client.statement.HttpResponse
import io.ktor.client.engine.cio.CIO
import io.ktor.client.network.sockets.ConnectTimeoutException
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.request.get
import io.ktor.client.request.request
import io.ktor.client.statement.bodyAsText
import io.ktor.client.statement.request
import io.ktor.http.charset
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.net.SocketException
import kotlin.math.log


const val REQUEST_INTERVAL_MS:Long  =  10_000L
const val URL: String =  "http://192.168.1.1/" // TODO url selector

class WeatherStationViewModel : ViewModel() {
    private var isActive = true
    private val client = HttpClient(CIO)
    private var _weatherInfo = MutableStateFlow(WeatherInfo())
    var weatherInfo = _weatherInfo.asStateFlow()

    init {
        viewModelScope.launch {
           fetchWeatherInfo(URL)
        }
    }

    private suspend fun fetchWeatherInfo(url: String) {

        while (isActive) {
            try {
                _weatherInfo.value = WeatherInfo()  //todo
                val response = client.get(url)
                val responseBody = response.bodyAsText()
                Log.d("fetchWeatherInfo response", response.toString())
                Log.d("fetchWeatherInfo content", responseBody)
                try {
                    this._weatherInfo.value  = Json.decodeFromString<WeatherInfo>(responseBody);
                    Log.d("fetchWeatherInfo JSON", this._weatherInfo.value.toString())
                } catch (e:SerializationException)
                {
                    Log.w("fetchWeatherInfo json",e.toString())

                } catch (e:IllegalArgumentException){
                    Log.w("fetchWeatherInfo json",e.toString())
                }

            } catch (e: ConnectTimeoutException) {
                Log.i("fetchWeatherInfo", "Connect the freaking station")
                Log.e("fetchWeatherInfo", e.toString())
            } catch (e: SocketTimeoutException) {
                Log.i("fetchWeatherInfo", "Connect the freaking wifi!!!")
                Log.e("fetchWeatherInfo", e.toString())
            } catch (e: SocketException) {
                Log.i("fetchWeatherInfo", "Connect the freaking station!!!")
                Log.e("fetchWeatherInfo", e.toString())
            } finally {
                delay(REQUEST_INTERVAL_MS)
            }
        }
    }
}