package com.alex.myweather.domain.repository

import com.alex.myweather.domain.model.CurrentWeatherData
import com.alex.myweather.domain.model.DailyWeatherData
import com.alex.myweather.domain.model.HourlyWeatherData
import kotlinx.coroutines.flow.Flow

interface LocalWeatherRepository {

    suspend fun saveCurrentWeatherData(value: CurrentWeatherData)

    suspend fun saveDailyWeatherData(values: DailyWeatherData)

    suspend fun saveHourlyWeatherData(values: HourlyWeatherData)

    fun observeCurrentWeatherData(): Flow<CurrentWeatherData>

    fun observeDailyWeatherData(): Flow<List<DailyWeatherData>>

    fun observeHourlyWeatherData(): Flow<List<HourlyWeatherData>>
}
