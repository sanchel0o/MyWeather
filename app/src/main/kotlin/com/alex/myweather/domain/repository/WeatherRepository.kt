package com.alex.myweather.domain.repository

import com.alex.myweather.domain.model.ForecastData

interface WeatherRepository {
    suspend fun getData(): ForecastData
}