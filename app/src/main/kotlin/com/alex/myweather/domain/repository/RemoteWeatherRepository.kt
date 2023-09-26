package com.alex.myweather.domain.repository

import com.alex.myweather.domain.model.ForecastData

interface RemoteWeatherRepository {
    suspend fun loadForecastData(city: String, days: Int): ForecastData
}
