package com.alex.myweather.domain.model

data class CurrentWeatherData(
    val temperature: Int,
    val pressure: Int,
    val humidity: Int,
    val windSpeed: Int,
    val condition: String,
    val imageUrl: String,
)
