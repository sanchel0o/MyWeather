package com.alex.myweather.data.model.dto

import kotlinx.serialization.Serializable

@Serializable
data class ConditionDto(
    val code: Int,
    val icon: String,
    val text: String
)