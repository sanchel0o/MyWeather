package com.alex.myweather.domain.model

data class HourlyWeatherData(
    val time: String,
    val temperature: Int,
    val imageUrl: String,
)
