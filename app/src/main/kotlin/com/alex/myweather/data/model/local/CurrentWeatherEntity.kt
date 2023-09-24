package com.alex.myweather.data.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "current_weather")
data class CurrentWeatherEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "date") val date: String?,
    @ColumnInfo(name = "temperature") val temp: String?,
    val pressure: Int,
    val humidity: Int,
    @ColumnInfo(name = "wind_speed") val windSpeed: Int,
    @ColumnInfo(name = "weather_icon") val weatherIcon: String?,
    @ColumnInfo(name = "hourly_weather_data") val hourlyWeatherData: String?
)
