package com.alex.myweather.data.repository

import com.alex.myweather.BuildConfig
import com.alex.myweather.data.api.WeatherApi
import com.alex.myweather.data.mapper.toDomain
import com.alex.myweather.domain.model.ForecastData
import com.alex.myweather.domain.repository.RemoteWeatherRepository
import javax.inject.Inject

class RemoteWeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi
) : RemoteWeatherRepository {

    override suspend fun loadForecastData(city: String, days: Int): ForecastData = api
        .getWeatherForecast(
            key = BuildConfig.API_KEY,
            city = city,
            days = days,
        )
        .toDomain()
}
