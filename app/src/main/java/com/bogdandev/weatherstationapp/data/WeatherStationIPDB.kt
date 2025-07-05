package com.bogdandev.weatherstationapp.data

import SavedProvidersDao
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [SavedProviders::class],
    version = 1
)
abstract class WeatherStationIPDB : RoomDatabase() {
    abstract val savedIPDao: SavedProvidersDao
}