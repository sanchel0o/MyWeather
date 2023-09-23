package com.alex.myweather.data.model.remote

import kotlinx.serialization.Serializable

@Serializable
data class WeatherForecastDto(
    val current: Current,
    val forecast: Forecast,
    val location: Location
)