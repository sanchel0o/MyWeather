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
import com.alex.myweather.presentation.main_screen.components.dayOfMonth
import java.time.LocalDate

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = hiltViewModel()
) {

    val currentWeatherState by viewModel.currentWeatherDataFlow.collectAsStateWithLifecycle()
    val dailyWeatherState by viewModel.dailyWeatherDataFlow.collectAsStateWithLifecycle()
    val hourlyWeatherState by viewModel.hourlyWeatherDataFlow.collectAsStateWithLifecycle()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),

        topBar = {
            MainScreenTopAppBar(
                onMenuButtonClick = {  },
                onAddCityButtonClick =  {  }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top
            ) {
                CurrentWeatherData(
                    imageUrl = currentWeatherState?.imageUrl ?: "",
                    currentTemperature = currentWeatherState?.temperature,
                    unit = stringResource(id = R.string.degree_symbol),
                    currentCondition = currentWeatherState?.condition,
                    date = dayOfMonth
                )

                WeatherInfoCard(
                    pressure = currentWeatherState?.pressure,
                    humidity = currentWeatherState?.humidity,
                    windSpeed = currentWeatherState?.windSpeed
                )

                LazyRow(
                    modifier = Modifier
                        .padding(horizontal = MEDIUM_PADDING.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(space = 12.dp),

                    ) {
                    items(hourlyWeatherState) { item ->
                        HourlyWeatherCard(
                            time = item.time,
                            temperature = item.temperature,
                            unit = stringResource(id = R.string.degree_symbol),
                            imageUrl = item.imageUrl
                        )
                    }
                }

                DailyForecastCard {
                    LazyColumn {
                        items(dailyWeatherState) { item ->
                            SingleDayForecast(
                                date = item.day,
                                dayHumidity = item.humidity,
                                imageUrl = item.imageUrl,
                                maxDayTemperature = item.maxTemp,
                                minDayTemperature = item.minTemp,
                                degreeUnit = stringResource(id = R.string.degree_symbol)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
@MyWeatherPreview
private fun Preview() = MyWeatherPreview {
    MainScreen()
}
