package com.alex.myweather.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.alex.myweather.data.model.local.DailyWeatherEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DailyForecastDao {

    @Query("SELECT * FROM daily_forecast")
    fun getDailyForecastData() : Flow<DailyWeatherEntity>

    @Upsert
    suspend fun upsertCurrentWeatherData() // TODO - add content into
}
