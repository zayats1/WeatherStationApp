package com.bogdandev.weatherstationapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bogdandev.weatherstationapp.R


@Composable
fun Weather(modifier: Modifier = Modifier) {
    Surface(modifier) {
        Column(
            modifier.padding(top = 25.dp)
        )
        {
            DateAndTimeBar(modifier)
            WeatherBar(
                data = "101.325 kpa,",
                modifier = modifier
            )
            TemperatureBar(Modifier)
            Humidity(modifier)
        }
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
        Weather(Modifier)
    }
}


@Composable
fun DisplayBar(
    content: @Composable RowScope.(modifier: Modifier) -> Unit,
    modifier: Modifier = Modifier
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
fun TemperatureBar(modifier: Modifier = Modifier) {
    DisplayBar(
        modifier = modifier,
        content = {
            Text(
                text = "20*C",
                modifier = modifier.padding(start = 10.dp, end = 20.dp)
            )
            Image(
                painter = painterResource(
                    R.drawable.termometer_foreground
                ),
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .size(150.dp)
                    .padding(end = 2.dp)
                    .clip(CircleShape),

                contentDescription = null
            )
        })
}


@Preview(showBackground = true)
@Composable
fun Humidity(modifier: Modifier = Modifier) {
    DisplayBar(modifier = modifier.height(150.dp), content = {
        Text(
            text = "80%",
            modifier = modifier.padding(start = 10.dp, end = 20.dp)
        )
        Image(
            painter = painterResource(
                R.drawable.humidity_foreground
            ),
            contentScale = ContentScale.Crop,
            modifier = modifier
                .size(150.dp)
                .padding(end = 2.dp)
                .clip(CircleShape),

            contentDescription = null
        )
    })
}


@Preview(showBackground = true)
@Composable
fun DateAndTimeBar(modifier: Modifier = Modifier) {
    DisplayBar(modifier = modifier.height(50.dp), content = {
        Text(
            text = "Lviv 16:36  07.07.2025",
            modifier = modifier.padding(start = 10.dp, end = 20.dp)
        )
    })
}


@Preview(showBackground = true)
@Composable
fun WeatherBar(modifier: Modifier = Modifier, data: String = "Lviv 20*C Sunny") {
    DisplayBar(modifier = modifier, content = {
        Text(
            text = data,
            modifier = modifier.padding(end = 20.dp)
        )

        Image(
            painter = painterResource(
                R.drawable.sun_foreground
            ),
            contentScale = ContentScale.Crop,
            modifier = modifier
                .size(80.dp)
                .padding(end = 2.dp)
                .clip(CircleShape)
                .background(Color(0xFF37AAE5)),

            contentDescription = null
        )
    })
}





