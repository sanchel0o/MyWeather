package com.alex.myweather.data.api

import com.alex.myweather.data.model.remote.WeatherForecastDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("v1/forecast.json?")
    suspend fun getWeatherForecast(
        @Query("key") key: String,
        @Query("q") city: String,
        @Query("days") days: Int
    ): WeatherForecastDto
}

//Example for api request
//http://api.weatherapi.com/v1/forecast.json?key=1b3d3a413a704c2082784210231207&q=Penza&days=5

// When use @path key={key}&q={city}&days={days}
//Exception code is: URL query string "key={key}&q={city}&days={days}" must not have replace block. For dynamic query parameters use @Query.
//for method WeatherApi.getWeatherForecast
