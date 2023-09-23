package com.alex.myweather.data.api

import com.alex.myweather.data.model.remote.WeatherForecastDto
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherApi {

    @GET("v1/forecast.json?key={key}&q={city}&days={days}")
    suspend fun getWeatherForecast(
        @Path("key") key: String,
        @Path("city") city: String,
        @Path("days") days: Int
    ): WeatherForecastDto
}

//Example for api request
//http://api.weatherapi.com/v1/forecast.json?key=1b3d3a413a704c2082784210231207&q=Penza&days=5
