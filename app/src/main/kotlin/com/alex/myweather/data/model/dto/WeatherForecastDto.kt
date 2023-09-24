package com.alex.myweather.data.model.dto

import kotlinx.serialization.Serializable

@Serializable
data class WeatherForecastDto(
    val location: LocationDto,
    val current: CurrentDto,
    val forecast: ForecastDto
)