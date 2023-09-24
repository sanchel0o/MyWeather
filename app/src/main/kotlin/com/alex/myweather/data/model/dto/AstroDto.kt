package com.alex.myweather.data.model.dto

import kotlinx.serialization.Serializable

@Serializable
data class AstroDto(
    val is_moon_up: Int,
    val is_sun_up: Int,
    val moon_illumination: String,
    val moon_phase: String,
    val moonrise: String,
    val moonset: String,
    val sunrise: String,
    val sunset: String
)