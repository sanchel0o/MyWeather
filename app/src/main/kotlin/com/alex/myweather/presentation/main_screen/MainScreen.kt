package com.alex.myweather.presentation.main_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alex.myweather.R
import com.alex.myweather.core.ui_utils.preview.MyWeatherPreview
import com.alex.myweather.core.ui_utils.theme.MEDIUM_PADDING
import com.alex.myweather.presentation.main_screen.components.CurrentWeatherData
import com.alex.myweather.presentation.main_screen.components.DailyForecastCard
import com.alex.myweather.presentation.main_screen.components.HourlyWeatherCard
import com.alex.myweather.presentation.main_screen.components.MainScreenTopAppBar
import com.alex.myweather.presentation.main_screen.components.SingleDayForecast
import com.alex.myweather.presentation.main_screen.components.WeatherInfoCard
import java.time.LocalDate

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = hiltViewModel()
) {
    //val forecastState by viewModel.forecastDataState.collectAsStateWithLifecycle()

//    if (forecastState.isLoading) {
//        Box(modifier = Modifier.fillMaxSize()) {
//            LinearProgressIndicator(
//                modifier = Modifier
//            )
//        }
//    } else {
//        Scaffold(
//            modifier = Modifier
//                .fillMaxSize()
//                .statusBarsPadding(),
//
//            topBar = {
//                MainScreenTopAppBar(
//                    onMenuButtonClick = {},
//                    onAddCityButtonClick = {}
//                )
//            }
//        ) { paddingValues ->
//            Box(
//                Modifier
//                    .fillMaxSize()
//                    .padding(paddingValues),
//            ) {
//                Column(
//                    modifier = Modifier.fillMaxSize(),
//                    verticalArrangement = Arrangement.Top
//                ) {
//                    CurrentWeatherData(
//                        imageUrl = forecastState.currentWeatherData?.imageUrl
//                            ?: "http://cdn.weatherapi.com/weather/64x64/day/116.png",
//                        currentTemperature = forecastState.currentWeatherData?.temperature,
//                        unit = stringResource(id = R.string.degree_symbol),
//                        date = LocalDate.now()
//                    )
//
//                    WeatherInfoCard(
//                        pressure = forecastState.currentWeatherData?.pressure,
//                        humidity = forecastState.currentWeatherData?.humidity,
//                        windSpeed = forecastState.currentWeatherData?.windSpeed
//                    )
//
//                    LazyRow(
//                        modifier = Modifier
//                            .padding(horizontal = MEDIUM_PADDING.dp)
//                            .fillMaxWidth(),
//                        horizontalArrangement = Arrangement.spacedBy(space = 12.dp),
//
//                        ) {
//                        items(forecastState.hourlyWeatherData) { item ->
//                            HourlyWeatherCard(
//                                time = item.time,
//                                temperature = item.temperature,
//                                unit = stringResource(id = R.string.degree_symbol),
//                                imageUrl = item.imageUrl
//                            )
//                        }
//
//                    }
//
//                    DailyForecastCard {
//                        LazyColumn {
//                            items(forecastState.dailyWeatherData) { item ->
//                                SingleDayForecast(
//                                    date = item.day.toString(),
//                                    dayHumidity = item.humidity,
//                                    imageUrl = item.imageUrl,
//                                    maxDayTemperature = item.maxTemp,
//                                    minDayTemperature = item.minTemp,
//                                    degreeUnit = stringResource(id = R.string.degree_symbol)
//                                )
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }

}

@Composable
@MyWeatherPreview
private fun Preview() = MyWeatherPreview {
    MainScreen()
}
