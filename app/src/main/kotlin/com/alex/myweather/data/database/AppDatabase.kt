package com.alex.myweather.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alex.myweather.data.dao.CurrentWeatherDao
import com.alex.myweather.data.dao.DailyForecastDao
import com.alex.myweather.data.model.local.CurrentWeatherEntity
import com.alex.myweather.data.model.local.DailyWeatherEntity

@Database(
    version = 1,
    entities = [
        CurrentWeatherEntity::class,
        DailyWeatherEntity::class
    ]
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun currentWeatherDao() : CurrentWeatherDao

    abstract fun dailyWeatherDao() : DailyForecastDao
}