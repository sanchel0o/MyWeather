package com.alex.myweather.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.alex.myweather.data.model.entity.CurrentWeatherEntity
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface CurrentWeatherDao {

    @Query("SELECT * FROM current_weather WHERE id=:id")
    fun observeCurrentWeatherData(id : UUID) : Flow<CurrentWeatherEntity>

    @Upsert
    suspend fun upsertCurrentWeatherData(value : CurrentWeatherEntity)
}
