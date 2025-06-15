package com.bogdandev.weatherstationapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bogdandev.weatherstationapp.R
import com.bogdandev.weatherstationapp.app.WeatherStationViewModel
import java.util.Calendar
import java.util.Date


@Composable
fun Weather(modifier: Modifier = Modifier,model: WeatherStationViewModel = WeatherStationViewModel()) {
    val weatherInfo by model.weatherInfo.collectAsStateWithLifecycle()
    Surface(modifier) {
        Column(
            modifier.padding(top = 25.dp)
        )
        {
            DateAndTimeBar(modifier)
            PressureBar(
                pressure =weatherInfo.pressure ,
                modifier = modifier
            )
            TemperatureBar(
                temperature = weatherInfo.temperature,
                modifier = modifier)
            Humidity(
                humidity = weatherInfo.humidity,
                modifier = modifier)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun WeatherPreview(weatherStationViewModel: WeatherStationViewModel = WeatherStationViewModel()) {
    Surface(
        modifier = Modifier
            .padding(top = 25.dp)
            .height(2000.dp)
            .width(1080.dp)
    ) {
        Weather(Modifier)
    }
}


@Composable
fun DisplayBar(
    modifier: Modifier = Modifier,
    weatherStationViewModel: WeatherStationViewModel = WeatherStationViewModel(),
            content: @Composable RowScope.(modifier: Modifier) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .padding(5.dp)
            .padding(top = 5.dp)
            .fillMaxWidth()
            .height(150.dp)
    ) {
        content(modifier)
    }
}


@Preview(showBackground = true)
@Composable
fun TemperatureBar(modifier: Modifier = Modifier,
        temperature:Double = 0.0) {
    DisplayBar(modifier) {

            Text(
                text = "$temperature *C",
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
fun Humidity(modifier: Modifier = Modifier, humidity:Double = 0.0) {
    DisplayBar(modifier = modifier.height(150.dp)) {
        Text(
            text = "$humidity%",
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
fun PressureBar(modifier: Modifier = Modifier, pressure: Double = 101.315) {
    DisplayBar(modifier) {
        Text(
            text = "$pressure kPa",
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





