package com.alex.myweather.data.model.dto

import kotlinx.serialization.Serializable

@Serializable
data class ForecastDto(
    val forecastday: List<ForecastdayDto>
)