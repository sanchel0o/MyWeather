package com.alex.myweather.domain.repository

import com.alex.myweather.domain.model.CurrentWeatherData
import com.alex.myweather.domain.model.DailyWeatherData
import com.alex.myweather.domain.model.HourlyWeatherData
import kotlinx.coroutines.flow.Flow

interface LocalWeatherRepository {

    suspend fun saveCurrentWeatherData(value: CurrentWeatherData)

    suspend fun saveDailyWeatherData(value: DailyWeatherData)

    suspend fun saveHourlyWeatherData(value: HourlyWeatherData)

    fun observeCurrentWeatherData(): Flow<CurrentWeatherData>

    fun observeDailyWeatherData(): Flow<DailyWeatherData>

    fun observeHourlyWeatherData(): Flow<HourlyWeatherData>
}
