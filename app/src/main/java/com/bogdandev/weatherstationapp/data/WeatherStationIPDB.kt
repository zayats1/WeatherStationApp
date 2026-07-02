package com.bogdandev.weatherstationapp.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [SavedProviders::class],
    version = 1
)
abstract class WeatherStationIPDB : RoomDatabase() {
    abstract val savedProvidersDao: SavedProvidersDao
}