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
import kotlinx.coroutines.flow.mapNotNull
import java.util.UUID
import javax.inject.Inject

class LocalWeatherRepositoryImpl @Inject constructor(
    private val currentWeatherDao: CurrentWeatherDao,
    private val dailyWeatherDao: DailyWeatherDao,
    private val hourlyWeatherDao: HourlyWeatherDao
) : LocalWeatherRepository {

    override suspend fun saveCurrentWeatherData(value: CurrentWeatherData) {
        currentWeatherDao.upsertCurrentWeatherData(value.toEntity())
    }

    override suspend fun saveDailyWeatherData(values: DailyWeatherData) {
        dailyWeatherDao.upsertCurrentWeatherData(values.toEntity())
    }

    override suspend fun saveHourlyWeatherData(values: HourlyWeatherData) {
        hourlyWeatherDao.upsertHourlyWeatherData(values.toEntity())
    }

    override fun observeCurrentWeatherData(): Flow<CurrentWeatherData> = currentWeatherDao
        .observeCurrentWeatherData()
        .mapNotNull { it.firstOrNull() }
        .map { it.toDomain() }

    override fun observeDailyWeatherData(): Flow<List<DailyWeatherData>> = dailyWeatherDao
        .observeDailyWeatherData()
        .map { dailyWeatherEntities -> dailyWeatherEntities.map { it.toDomain() } }

    override fun observeHourlyWeatherData(): Flow<List<HourlyWeatherData>> = hourlyWeatherDao
        .observeHourlyWeatherData()
        .map { hourlyWeatherEntities ->
            hourlyWeatherEntities.map { it.toDomain() }
        }
}
