package com.alex.myweather.data.mappers

import com.alex.myweather.data.model.remote.Current
import com.alex.myweather.data.model.remote.Forecastday
import com.alex.myweather.data.model.remote.Hour
import com.alex.myweather.data.model.remote.WeatherForecastDto
import com.alex.myweather.domain.model.CurrentWeatherData
import com.alex.myweather.domain.model.DailyWeatherData
import com.alex.myweather.domain.model.ForecastData
import com.alex.myweather.domain.model.HourlyWeatherData
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

fun Current.toCurrentWeatherData(): CurrentWeatherData = CurrentWeatherData(
    temperature = this.temp_c,
    pressure = this.pressure_mb,
    humidity = this.humidity,
    windSpeed = this.wind_kph.roundToInt(),
    imageUrl = this.condition.icon,
    condition = this.condition.text
)

fun Hour.toHourlyWeatherData(): HourlyWeatherData = HourlyWeatherData(
    time = this.time,
    temperature = this.temp_c.roundToInt(),
    imageUrl = this.condition.icon
)

fun Forecastday.toDailyWeatherData(): DailyWeatherData = DailyWeatherData(
    day = LocalDateTime.parse(this.date,formatter),
    maxTemp = this.day.maxtemp_c.roundToInt(),
    minTemp = this.day.mintemp_c.roundToInt(),
    humidity = this.day.avghumidity,
    imageUrl = this.day.condition.icon
)

fun WeatherForecastDto.toForecastData() = ForecastData(
    currentWeatherData = this.current.toCurrentWeatherData(),
    hourlyWeatherData = this.forecast.forecastday.first().hour.map { it.toHourlyWeatherData() },
    dailyWeatherData = this.forecast.forecastday.map { it.toDailyWeatherData() }
)
