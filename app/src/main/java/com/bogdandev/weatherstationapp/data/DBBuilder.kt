package com.bogdandev.weatherstationapp.data

import android.content.Context
import androidx.room.Room

object DBBuilder {
    private var INSTANCE: WeatherStationIPDB? = null
    fun getInstance(context: Context): WeatherStationIPDB {
        if (INSTANCE == null) {
            synchronized(WeatherStationIPDB::class) {
                INSTANCE = buildRoomDB(context)
            }
        }
        return INSTANCE!!
    }

    private fun buildRoomDB(context: Context) =
        Room.databaseBuilder(
            context.applicationContext,
            WeatherStationIPDB::class.java,
            "WeatherStationIPDB"
        ).build()
}
