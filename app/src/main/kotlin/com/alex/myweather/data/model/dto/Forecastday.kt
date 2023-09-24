package com.alex.myweather.data.model.dto

import kotlinx.serialization.Serializable

@Serializable
data class Forecastday(
    val astro: AstroDto,
    val date: String,
    val date_epoch: Int,
    val day: DayDto,
    val hour: List<HourDto>
)