package com.alex.myweather.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.alex.myweather.data.model.entity.DailyWeatherEntity
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface DailyWeatherDao {

    @Query("SELECT * FROM daily_weather WHERE id=:id")
    fun observeDailyWeatherData(id : UUID) : Flow<DailyWeatherEntity>

    @Upsert
    suspend fun upsertCurrentWeatherData(value: DailyWeatherEntity)
}
