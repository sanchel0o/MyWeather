package com.alex.myweather.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "current_weather")
data class CurrentWeatherEntity(
    @PrimaryKey val id: UUID,
    val temperature: Int,
    val pressure: Int,
    val humidity: Int,
    val windSpeed: Int,
    val imageUrl: String,
    val condition: String,
)
