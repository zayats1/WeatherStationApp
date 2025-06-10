package com.bogdandev.weatherstationapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.bogdandev.weatherstationapp.ui.theme.TheAppTheme


@Composable
fun Weather(modifier: Modifier = Modifier) {
    Surface(modifier = modifier) {
        Column(
            modifier = Modifier.padding(top = 25.dp)
        )
        {
            DateAndTimeBar(modifier = modifier)
            WeatherBar(
                content = "Sunny\n temp: 20*C\n humidity: 80%\n Pressure: 101.325,",
                modifier = modifier
            )
            TemperatureBar(modifier = Modifier)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TemperatureBar(modifier: Modifier = Modifier){
    Row (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
        .padding(5.dp)
        .padding(top = 5.dp)
        .fillMaxWidth()
        .height(150.dp)) {
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
    }
}



@Preview(showBackground = true)
@Composable
fun DateAndTimeBar(modifier: Modifier = Modifier){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .padding(5.dp)
            .padding(top = 5.dp)
            .fillMaxWidth()
            .height(50.dp)
    ) {
        Text(
            text = "Lviv 16:36  07.07.2025",
            modifier = modifier.padding(start = 10.dp, end = 20.dp)
        )
    }
}



@Composable
fun WeatherBar(content: String, modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .padding(1.dp)
            .padding(top = 5.dp)
            .fillMaxWidth()
            .height(250.dp)
    ) {

        Text(
            text = content,
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
    }
}


@Preview(showBackground = true)
@Composable
fun WeatherPreview() {
    Surface (modifier = Modifier.padding(top = 25.dp).height(2000.dp)
        .width(1080.dp)){
        Weather(Modifier)
    }
}



@Preview(showBackground = true)
@Composable
fun WeatherBarPreview() {
    TheAppTheme {
        WeatherBar("Lviv 20*C Sunny")
    }
}