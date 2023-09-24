package com.alex.myweather.data.model.remote

import kotlinx.serialization.Serializable

@Serializable
data class Location(
    val name: String,
    val country: String,
    val region: String,
    val lat: Double,
    val lon: Double,
    val tz_id: String,
    val localtime_epoch: Int,
    val localtime: String
)