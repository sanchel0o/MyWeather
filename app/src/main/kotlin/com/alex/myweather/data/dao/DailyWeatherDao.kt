package com.alex.myweather.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.alex.myweather.data.model.entity.DailyWeatherEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DailyWeatherDao {

    @Query("SELECT * FROM daily_weather")
    fun observeDailyWeatherData() : Flow<List<DailyWeatherEntity>>

    @Upsert
    suspend fun upsertCurrentWeatherData(vararg values: DailyWeatherEntity)
}
