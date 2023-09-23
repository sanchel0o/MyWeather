package com.alex.myweather.data.repository

import com.alex.myweather.data.api.WeatherApi
import com.alex.myweather.data.mappers.toForecastData
import com.alex.myweather.domain.model.ForecastData
import com.alex.myweather.domain.repository.WeatherRepository
import javax.inject.Inject

const val API_KEY = "1b3d3a413a704c2082784210231207"
const val CITY = "Penza"
const val DAYS = 5

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi
) : WeatherRepository {
    override suspend fun getData(): ForecastData =
        api.getWeatherForecast(key = API_KEY, city = CITY, days = DAYS).toForecastData()
}
