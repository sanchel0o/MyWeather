package com.alex.myweather.data.repository

import com.alex.myweather.data.dao.CurrentWeatherDao
import com.alex.myweather.data.dao.DailyWeatherDao
import com.alex.myweather.data.dao.HourlyWeatherDao
import com.alex.myweather.data.mapper.toDomain
import com.alex.myweather.data.mapper.toEntity
import com.alex.myweather.domain.model.CurrentWeatherData
import com.alex.myweather.domain.model.DailyWeatherData
import com.alex.myweather.domain.model.HourlyWeatherData
import com.alex.myweather.domain.repository.LocalWeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.UUID
import javax.inject.Inject

private val DEFAULT_UUID = UUID.fromString("0")

class LocalWeatherRepositoryImpl @Inject constructor(
    private val currentWeatherDao: CurrentWeatherDao,
    private val dailyWeatherDao: DailyWeatherDao,
    private val hourlyWeatherDao: HourlyWeatherDao
) : LocalWeatherRepository {

    override suspend fun saveCurrentWeatherData(value: CurrentWeatherData) {
        currentWeatherDao.upsertCurrentWeatherData(value.toEntity())
    }

    override suspend fun saveDailyWeatherData(value: DailyWeatherData) {
        dailyWeatherDao.upsertCurrentWeatherData(value.toEntity())
    }

    override suspend fun saveHourlyWeatherData(value: HourlyWeatherData) {
        hourlyWeatherDao.upsertHourlyWeatherData(value.toEntity())
    }

    override fun observeCurrentWeatherData(): Flow<CurrentWeatherData> = currentWeatherDao
        .observeCurrentWeatherData(id = DEFAULT_UUID)
        .map { it.toDomain() }

    override fun observeDailyWeatherData(): Flow<DailyWeatherData> = dailyWeatherDao
        .observeDailyWeatherData(id = DEFAULT_UUID)
        .map { it.toDomain() }

    override fun observeHourlyWeatherData(): Flow<HourlyWeatherData> = hourlyWeatherDao
        .observeHourlyWeatherData()
        .map { it.toDomain() }
}