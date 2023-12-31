package com.alex.myweather.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hourly_weather")
data class HourlyWeatherEntity (
    @PrimaryKey val time: String,
    val temperature: Int,
    val imageUrl: String,
)
