package com.alex.myweather.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "hourly_weather")
data class HourlyWeatherEntity (
    @PrimaryKey val id: UUID,
    val time: String,
    val temperature: Int,
    val imageUrl: String,
)
