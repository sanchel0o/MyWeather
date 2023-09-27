package com.alex.myweather.presentation.main_screen

import com.alex.myweather.domain.model.CurrentWeatherData
import com.alex.myweather.domain.model.DailyWeatherData
import com.alex.myweather.domain.model.HourlyWeatherData

data class MainScreenState(
    val foregroundServicePermission: Boolean = false,
    val currentWeatherData: CurrentWeatherData? = null,
    val dailyWeatherData: List<DailyWeatherData> = emptyList(),
    val hourlyWeatherData: List<HourlyWeatherData> = emptyList(),
)
