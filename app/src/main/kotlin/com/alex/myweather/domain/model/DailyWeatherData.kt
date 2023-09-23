package com.alex.myweather.domain.model

import java.time.LocalDateTime

data class DailyWeatherData(
    val day: LocalDateTime,
    val maxTemp: Int,
    val minTemp: Int,
    val humidity: Int,
    val imageUrl: String,
)
