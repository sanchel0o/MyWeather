package com.alex.myweather.data.database

import androidx.room.Database
import com.alex.myweather.data.model.local.CurrentWeatherEntity
import com.alex.myweather.data.model.local.DailyWeatherEntity

@Database(
    version = 1,
    entities = [
        CurrentWeatherEntity::class,
        DailyWeatherEntity::class
    ]
)
abstract class AppDatabase {
}