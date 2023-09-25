package com.alex.myweather.data.mapper

import com.alex.myweather.data.model.dto.CurrentDto
import com.alex.myweather.data.model.dto.Forecastday
import com.alex.myweather.data.model.dto.HourDto
import com.alex.myweather.data.model.dto.WeatherForecastDto
import com.alex.myweather.domain.model.CurrentWeatherData
import com.alex.myweather.domain.model.DailyWeatherData
import com.alex.myweather.domain.model.ForecastData
import com.alex.myweather.domain.model.HourlyWeatherData
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

private val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

private val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")

private val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")

private const val MERCURY_COEFFICIENT = 0.75f

private const val URL_PREFIX = "http:"

fun CurrentDto.toDomain(): CurrentWeatherData = CurrentWeatherData(
    temperature = this.temp_c.roundToInt(),
    pressure = mBarToMm(this.pressure_mb).roundToInt(),
    humidity = this.humidity,
    windSpeed = this.wind_kph.roundToInt(),
    imageUrl = URL_PREFIX + this.condition.icon,
    condition = this.condition.text
)

fun HourDto.toDomain(): HourlyWeatherData = HourlyWeatherData(
    time = getTimeFromString(this.time),
    temperature = this.temp_c.roundToInt(),
    imageUrl = URL_PREFIX + this.condition.icon
)

fun Forecastday.toDomain(): DailyWeatherData = DailyWeatherData(
    day = LocalDate.parse(this.date, dateFormatter).dayOfWeek.toString(),
    maxTemp = this.day.maxtemp_c.roundToInt(),
    minTemp = this.day.mintemp_c.roundToInt(),
    humidity = this.day.avghumidity.roundToInt(),
    imageUrl = URL_PREFIX + this.day.condition.icon
)

fun WeatherForecastDto.toDomain() = ForecastData(
    currentWeatherData = this.current.toDomain(),
    hourlyWeatherData = this.forecast.forecastday.first().hour.map { it.toDomain() },
    dailyWeatherData = this.forecast.forecastday.map { it.toDomain() }
)

private fun getTimeFromString(dateTimeString: String): String = LocalDateTime
    .parse(dateTimeString, dateTimeFormatter)
    .toLocalTime()
    .format(timeFormatter)

private fun mBarToMm(value: Double): Double = value * MERCURY_COEFFICIENT
