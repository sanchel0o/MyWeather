package com.alex.myweather.domain.model

import java.time.LocalDate

data class DailyWeatherData(
    val day: LocalDate,
    val maxTemp: Int,
    val minTemp: Int,
    val humidity: Int,
    val imageUrl: String,
)
