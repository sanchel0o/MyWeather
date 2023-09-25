package com.alex.myweather.data.mapper

import com.alex.myweather.data.model.entity.CurrentWeatherEntity
import com.alex.myweather.data.model.entity.DailyWeatherEntity
import com.alex.myweather.data.model.entity.HourlyWeatherEntity
import com.alex.myweather.domain.model.CurrentWeatherData
import com.alex.myweather.domain.model.DailyWeatherData
import com.alex.myweather.domain.model.HourlyWeatherData
import java.util.UUID



fun CurrentWeatherData.toEntity(): CurrentWeatherEntity = CurrentWeatherEntity(
    id = UUID.randomUUID(),
    temperature = temperature,
    pressure = pressure,
    humidity = humidity,
    windSpeed = windSpeed,
    imageUrl = imageUrl,
    condition = condition,
)

fun CurrentWeatherEntity.toDomain(): CurrentWeatherData = CurrentWeatherData(
    temperature = temperature,
    pressure = pressure,
    humidity = humidity,
    windSpeed = windSpeed,
    imageUrl = imageUrl,
    condition = condition,
)


fun DailyWeatherData.toEntity(): DailyWeatherEntity = DailyWeatherEntity(
    id = UUID.randomUUID(),
    date = day,
    maxTemperature = maxTemp,
    minTemperature = minTemp,
    humidity = humidity,
    imageUrl = imageUrl,
)

fun DailyWeatherEntity.toDomain(): DailyWeatherData = DailyWeatherData(
    day = date,
    maxTemp = maxTemperature,
    minTemp = minTemperature,
    humidity = humidity,
    imageUrl = imageUrl,
)

fun HourlyWeatherData.toEntity(): HourlyWeatherEntity = HourlyWeatherEntity(
    id = UUID.randomUUID(),
    time = time,
    temperature = temperature,
    imageUrl = imageUrl,
)

fun HourlyWeatherEntity.toDomain(): HourlyWeatherData = HourlyWeatherData(
    time = time,
    temperature = temperature,
    imageUrl = imageUrl,
)
