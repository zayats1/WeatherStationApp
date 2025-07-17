package com.bogdandev.weatherstationapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SavedProviders(
    @PrimaryKey @ColumnInfo(name = "url") val url: String
)