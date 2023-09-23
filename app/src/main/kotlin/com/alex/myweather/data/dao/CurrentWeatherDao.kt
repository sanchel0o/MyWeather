package com.alex.myweather.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.alex.myweather.data.model.local.CurrentWeatherEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrentWeatherDao {
    @Query("SELECT * FROM current_weather")
    fun getCurrentWeatherData() : Flow<CurrentWeatherEntity>

    @Upsert
    suspend fun upsertCurrentWeatherData() // TODO - add content into
}
