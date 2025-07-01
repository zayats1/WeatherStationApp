package com.bogdandev.weatherstationapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bogdandev.weatherstationapp.R
import com.bogdandev.weatherstationapp.app.WeatherStationViewModel
import java.util.Calendar
import java.util.Date


@Composable
fun Weather(
    modifier: Modifier = Modifier,
    model: WeatherStationViewModel = WeatherStationViewModel(),
    navController: NavController = rememberNavController(),
) {
    val weatherInfo by model.weatherInfo.collectAsStateWithLifecycle()
    val isConnected by model.isConnected.collectAsStateWithLifecycle()
    val isSi by model.isSi.collectAsStateWithLifecycle()
    val (info, tempUnit, pressureUnit) = if (isSi) {
        Triple(weatherInfo, "*C", "kPa")
    } else {
        Triple(weatherInfo.toImperial(), "*F", "atm")
    }

    fun roundIt(value: Double): String = "%.3f".format(value)

    Surface(modifier) {
        Column(
            modifier.padding(top = 45.dp)
        )
        {
            DateAndTimeBar(modifier)
            PressureBar(
                pressure = roundIt(info.pressure) + pressureUnit,
                modifier = modifier
            )
            TemperatureBar(
                temperature = roundIt(info.temperature) + tempUnit,
                modifier = modifier
            )
            Humidity(
                humidity = roundIt(info.humidity) + "%",
                modifier = modifier
            )
            NetworkStatus(
                isConnected = isConnected,
                modifier = modifier
            )
            Settings(
                navController = navController,
                modifier = modifier
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Settings(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController(),
) {
    DisplayBar(modifier) {
        Text(
            text = "Settings",
            modifier = modifier.padding(start = 10.dp, end = 20.dp)
        )
        Button(
            modifier = modifier
                .size(150.dp)
                .padding(end = 2.dp)
                .clip(CircleShape),
            colors = ButtonColors(
                contentColor = Color.Magenta,
                disabledContainerColor = Color.Transparent,
                disabledContentColor = Color.Transparent,
                containerColor = Color.Transparent,
            ),
            onClick = {
                navController.navigate(Screen.SETTINGS.toString())
            }
        ) {
            Image(
                painter = painterResource(
                    R.drawable.settings_foreground
                ),
                contentDescription = null
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun NetworkStatus(modifier: Modifier = Modifier, isConnected: Boolean = false) {
    DisplayBar(modifier) {
        Text(
            text = if (isConnected) {
                "Connected"
            } else {
                "Connect to the station!"
            },
            modifier = modifier.padding(start = 10.dp, end = 20.dp)
        )
        Icon(
            painter = painterResource(
                R.drawable.network_status_foreground
            ),
            tint = if (isConnected) {
                Color.Green
            } else {
                Color.Red
            },
            modifier = modifier
                .size(150.dp)
                .padding(end = 2.dp)
                .clip(CircleShape),

            contentDescription = null
        )
    }
}


@Preview(showBackground = true)
@Composable
fun WeatherPreview() {
    Surface(
        modifier = Modifier
            .padding(top = 25.dp)
            .height(2000.dp)
            .width(1080.dp)
    ) {
        Weather(
            modifier = Modifier
        )
    }
}


@Preview(showBackground = true)
@Composable
fun TemperatureBar(
    modifier: Modifier = Modifier,
    temperature: String = "0*C"
) {
    DisplayBar(modifier) {

        Text(
            text = temperature,
            modifier = modifier.padding(start = 10.dp, end = 20.dp)
        )
        Image(
            painter = painterResource(
                R.drawable.thermometer_foreground
            ),
            contentScale = ContentScale.FillBounds,
            modifier = modifier
                .size(150.dp)
                .padding(end = 2.dp)
                .clip(CircleShape),

            contentDescription = null
        )
    }
}


@Preview(showBackground = true)
@Composable
fun Humidity(modifier: Modifier = Modifier, humidity: String = "0.0%") {
    DisplayBar(modifier = modifier.height(150.dp)) {
        Text(
            text = humidity,
            modifier = modifier.padding(start = 10.dp, end = 20.dp)
        )
        Image(
            painter = painterResource(
                R.drawable.humidity_foreground
            ),
            contentScale = ContentScale.FillBounds,
            modifier = modifier
                .size(150.dp)
                .padding(end = 2.dp)
                .clip(CircleShape),

            contentDescription = null
        )
    }
}


@Preview(showBackground = true)
@Composable
fun DateAndTimeBar(modifier: Modifier = Modifier) {
    DisplayBar(modifier = modifier.height(50.dp)) {
        val currentTime: Date = Calendar.getInstance().time
        Text(
            text = "$currentTime",
            modifier = modifier.padding(start = 10.dp, end = 20.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PressureBar(modifier: Modifier = Modifier, pressure: String = "0kPa") {
    DisplayBar(modifier) {
        Text(
            text = pressure,
            modifier = modifier.padding(end = 20.dp)
        )

        Image(
            painter = painterResource(
                R.drawable.pressure_foreground
            ),
            contentScale = ContentScale.FillBounds,
            modifier = modifier
                .size(150.dp)
                .padding(end = 2.dp)
                .clip(CircleShape),

            contentDescription = null
        )
    }
}





