package com.bogdandev.weatherstationapp.data

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) // will be created automatically
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDB(
        @ApplicationContext context: Context
    ): WeatherStationIPDB = DBBuilder.getInstance(context)

    @Provides
    @Singleton
    fun provideSavedProvidersDao(db: WeatherStationIPDB): SavedProvidersDao = db.savedProvidersDao
}