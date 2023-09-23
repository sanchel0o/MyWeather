package com.alex.myweather.data.model.remote

import kotlinx.serialization.Serializable

@Serializable
data class Location(
    val country: String,
    val lat: Double,
    val localtime: String,
    val localtime_epoch: Int,
    val lon: Int,
    val name: String,
    val region: String,
    val tz_id: String
)