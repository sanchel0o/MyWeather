package com.alex.myweather.domain.model

data class ForecastData(
    val currentWeatherData: CurrentWeatherData? = null,
    val hourlyWeatherData: List<HourlyWeatherData> = emptyList(),
    val dailyWeatherData: List<DailyWeatherData> = emptyList(),
)
