package com.alex.myweather.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alex.myweather.data.dao.CurrentWeatherDao
import com.alex.myweather.data.dao.DailyWeatherDao
import com.alex.myweather.data.dao.HourlyWeatherDao
import com.alex.myweather.data.model.entity.CurrentWeatherEntity
import com.alex.myweather.data.model.entity.DailyWeatherEntity
import com.alex.myweather.data.model.entity.HourlyWeatherEntity

@Database(
    version = 1,
    entities = [
        CurrentWeatherEntity::class,
        DailyWeatherEntity::class,
        HourlyWeatherEntity::class
    ]
)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun currentWeatherDao() : CurrentWeatherDao

    abstract fun dailyWeatherDao() : DailyWeatherDao

    abstract fun hourlyWeatherDao() : HourlyWeatherDao
}
