package com.alex.myweather.data.mappers

import com.alex.myweather.data.model.remote.Current
import com.alex.myweather.data.model.remote.Forecastday
import com.alex.myweather.data.model.remote.Hour
import com.alex.myweather.data.model.remote.WeatherForecastDto
import com.alex.myweather.domain.model.CurrentWeatherData
import com.alex.myweather.domain.model.DailyWeatherData
import com.alex.myweather.domain.model.ForecastData
import com.alex.myweather.domain.model.HourlyWeatherData
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

private val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

private const val URL_PREFIX = "http:"

fun Current.toCurrentWeatherData(): CurrentWeatherData = CurrentWeatherData(
    temperature = this.temp_c.roundToInt(),
    pressure = this.pressure_mb.roundToInt(),
    humidity = this.humidity,
    windSpeed = this.wind_kph.roundToInt(),
    imageUrl = URL_PREFIX + this.condition.icon,
    condition = this.condition.text
)

fun Hour.toHourlyWeatherData(): HourlyWeatherData = HourlyWeatherData(
    time = getTimeFromString(this.time),
    temperature = this.temp_c.roundToInt(),
    imageUrl = URL_PREFIX + this.condition.icon
)

fun Forecastday.toDailyWeatherData(): DailyWeatherData = DailyWeatherData(
    day = LocalDate.parse(this.date, dateFormatter),
    maxTemp = this.day.maxtemp_c.roundToInt(),
    minTemp = this.day.mintemp_c.roundToInt(),
    humidity = this.day.avghumidity.roundToInt(),
    imageUrl = URL_PREFIX + this.day.condition.icon
)

fun WeatherForecastDto.toForecastData() = ForecastData(
    currentWeatherData = this.current.toCurrentWeatherData(),
    hourlyWeatherData = this.forecast.forecastday.first().hour.map { it.toHourlyWeatherData() },
    dailyWeatherData = this.forecast.forecastday.map { it.toDailyWeatherData() }
)

fun getTimeFromString(dateTimeString: String): String {
    val dateTimePattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
    val dateTime = LocalDateTime.parse(dateTimeString, dateTimePattern)

    // Extract the time from LocalDateTime
    val time = dateTime.toLocalTime()

    // Format the time as HH:mm
    return time.format(DateTimeFormatter.ofPattern("HH:mm"))
}
