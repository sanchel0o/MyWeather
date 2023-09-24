package com.alex.myweather.domain.model

data class DailyWeatherData(
    val day: String,
    val maxTemp: Int,
    val minTemp: Int,
    val humidity: Int,
    val imageUrl: String,
)
