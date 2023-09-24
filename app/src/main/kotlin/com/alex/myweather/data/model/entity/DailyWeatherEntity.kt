package com.alex.myweather.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "daily_weather")
data class DailyWeatherEntity(
    @PrimaryKey val id: UUID,
    val date: String,
    val maxTemperature: Int,
    val minTemperature: Int,
    val humidity: Int,
    val imageUrl: String
)
