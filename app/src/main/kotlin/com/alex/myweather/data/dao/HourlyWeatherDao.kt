package com.alex.myweather.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.alex.myweather.data.model.entity.HourlyWeatherEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HourlyWeatherDao {

    @Query("SELECT * FROM hourly_weather")
    fun observeHourlyWeatherData() : Flow<List<HourlyWeatherEntity>>

    @Upsert
    suspend fun upsertHourlyWeatherData(vararg values: HourlyWeatherEntity)
}
