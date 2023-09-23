package com.alex.myweather.data.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "daily_forecast")
data class DailyWeatherEntity(
    @PrimaryKey val id: Int,
    val date: String?,
    @ColumnInfo(name = "max_temperature") val maxTemp: Int,
    @ColumnInfo(name = "min_temperature") val minTemp: Int,
    val humidity: Int,
    @ColumnInfo(name = "weather_icon") val weatherIcon: String
)
