package com.alex.myweather.data.di

import android.content.Context
import androidx.room.Room
import com.alex.myweather.data.dao.CurrentWeatherDao
import com.alex.myweather.data.dao.DailyWeatherDao
import com.alex.myweather.data.dao.HourlyWeatherDao
import com.alex.myweather.data.database.WeatherDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Provides
    @Singleton
    fun provideWeatherDatabase(@ApplicationContext context: Context): WeatherDatabase =
        Room.databaseBuilder(
            context,
            WeatherDatabase::class.java,
            "weather_database"
        ).build()

    @Provides
    @Singleton
    fun provideCurrentWeatherDao(weatherDatabase: WeatherDatabase): CurrentWeatherDao =
        weatherDatabase.currentWeatherDao()

    @Provides
    @Singleton
    fun provideDailyForecastDao(weatherDatabase: WeatherDatabase): DailyWeatherDao =
        weatherDatabase.dailyWeatherDao()

    @Provides
    @Singleton
    fun provideHourlyForecastDao(weatherDatabase: WeatherDatabase): HourlyWeatherDao =
        weatherDatabase.hourlyWeatherDao()
}
