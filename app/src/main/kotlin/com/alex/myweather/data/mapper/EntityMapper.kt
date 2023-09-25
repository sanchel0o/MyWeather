package com.alex.myweather.data.mapper

import com.alex.myweather.data.model.entity.CurrentWeatherEntity
import com.alex.myweather.data.model.entity.DailyWeatherEntity
import com.alex.myweather.data.model.entity.HourlyWeatherEntity
import com.alex.myweather.domain.model.CurrentWeatherData
import com.alex.myweather.domain.model.DailyWeatherData
import com.alex.myweather.domain.model.HourlyWeatherData
import java.util.UUID

private val DEFAULT_UUID = UUID.nameUUIDFromBytes("0".toByteArray())

//TODO - add this
fun CurrentWeatherData.toEntity(): CurrentWeatherEntity = CurrentWeatherEntity(
    id = DEFAULT_UUID,
    temperature = this.temperature,
    pressure = this.pressure,
    humidity = this.humidity,
    windSpeed = this.windSpeed,
    imageUrl = this.imageUrl,
    condition = this.condition,
)

fun CurrentWeatherEntity.toDomain(): CurrentWeatherData = CurrentWeatherData(
    temperature = this.temperature,
    pressure = this.pressure,
    humidity = this.humidity,
    windSpeed = this.windSpeed,
    imageUrl = this.imageUrl,
    condition = this.condition,
)


fun DailyWeatherData.toEntity(): DailyWeatherEntity = DailyWeatherEntity(
    date = this.day,
    maxTemperature = this.maxTemp,
    minTemperature = this.minTemp,
    humidity = this.humidity,
    imageUrl = this.imageUrl,
)

fun DailyWeatherEntity.toDomain(): DailyWeatherData = DailyWeatherData(
    day = this.date,
    maxTemp = this.maxTemperature,
    minTemp = this.minTemperature,
    humidity = this.humidity,
    imageUrl = this.imageUrl,
)

fun HourlyWeatherData.toEntity(): HourlyWeatherEntity = HourlyWeatherEntity(
    time = this.time,
    temperature = this.temperature,
    imageUrl = this.imageUrl,
)

fun HourlyWeatherEntity.toDomain(): HourlyWeatherData = HourlyWeatherData(
    time = this.time,
    temperature = this.temperature,
    imageUrl = this.imageUrl,
)
