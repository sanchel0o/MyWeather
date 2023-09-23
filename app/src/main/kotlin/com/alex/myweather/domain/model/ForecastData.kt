package com.alex.myweather.domain.model

data class ForecastData(
    val currentWeatherData: CurrentWeatherData,
    val hourlyWeatherData: List<HourlyWeatherData>,
    val dailyWeatherData: List<DailyWeatherData>
)
