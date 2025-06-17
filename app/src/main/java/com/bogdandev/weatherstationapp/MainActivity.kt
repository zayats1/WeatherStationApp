package com.bogdandev.weatherstationapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room.databaseBuilder
import com.bogdandev.weatherstationapp.app.WeatherStationViewModel
import com.bogdandev.weatherstationapp.data.DBBuilder
import com.bogdandev.weatherstationapp.data.SavedIP
import com.bogdandev.weatherstationapp.data.WeatherStationIPDB
import com.bogdandev.weatherstationapp.ui.Screen
import com.bogdandev.weatherstationapp.ui.SettingsScreen
import com.bogdandev.weatherstationapp.ui.Weather
import com.bogdandev.weatherstationapp.ui.theme.TheAppTheme
import kotlin.jvm.java


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val model = WeatherStationViewModel(context = baseContext)

        setContent {
            val navController = rememberNavController()

            TheAppTheme {
            NavHost(
                navController= navController,
                startDestination = Screen.MAIN.toString()
            ) {
                composable (route = Screen.MAIN.toString()){
                        Weather(modifier = Modifier, model =model, navController = navController)
                }
                composable (route = Screen.SETTINGS.toString()){
                    SettingsScreen(modifier = Modifier, model =model, navController = navController)
                    }
                }
            }

        }
    }
}


