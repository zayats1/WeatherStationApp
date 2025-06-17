package com.bogdandev.weatherstationapp.data

import SavedIPDao
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [SavedIP::class],
    version = 1
)
abstract class WeatherStationIPDB : RoomDatabase() {
    abstract val savedIPDao : SavedIPDao
}