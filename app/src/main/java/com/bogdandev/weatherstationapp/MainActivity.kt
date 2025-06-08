package com.bogdandev.weatherstationapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.Modifier
import com.bogdandev.weatherstationapp.ui.Weather

import com.bogdandev.weatherstationapp.ui.theme.TheAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TheAppTheme {
                Weather(modifier = Modifier)
            }
        }
    }
}


