package com.alex.myweather.presentation.main_screen

import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startForegroundService
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alex.myweather.MainActivity
import com.alex.myweather.R
import com.alex.myweather.core.ui_utils.preview.MyWeatherPreview
import com.alex.myweather.core.ui_utils.theme.MEDIUM_PADDING
import com.alex.myweather.presentation.main_screen.components.CurrentWeatherData
import com.alex.myweather.presentation.main_screen.components.DailyForecastCard
import com.alex.myweather.presentation.main_screen.components.HourlyWeatherCard
import com.alex.myweather.presentation.main_screen.components.MainScreenTopAppBar
import com.alex.myweather.presentation.main_screen.components.RequestPermissions
import com.alex.myweather.presentation.main_screen.components.SingleDayForecast
import com.alex.myweather.presentation.main_screen.components.WeatherInfoCard
import com.alex.myweather.presentation.main_screen.components.dayOfMonth
import com.alex.myweather.service.WeatherService

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = hiltViewModel()
) {
    val currentWeatherState by viewModel.currentWeatherDataFlow.collectAsStateWithLifecycle()
    val dailyWeatherState by viewModel.dailyWeatherDataFlow.collectAsStateWithLifecycle()
    val hourlyWeatherState by viewModel.hourlyWeatherDataFlow.collectAsStateWithLifecycle()
    val mainScreenState by viewModel.mainScreenState.collectAsStateWithLifecycle()
    val state by viewModel.state.collectAsState()

    val context = LocalContext.current
    val intent = Intent(context as MainActivity, WeatherService::class.java)

    LaunchedEffect(key1 = mainScreenState.foregroundServicePermission) {
        startForegroundService(context, intent)
    }

    RequestPermissions {
        viewModel.onEvent(MainScreenEvents.PermissionChanged)

        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding(),

            topBar = {
                MainScreenTopAppBar(
                    onMenuButtonClick = { },
                    onAddCityButtonClick = { }
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
                        imageUrl = state.currentWeatherData?.imageUrl ?: "",
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
}

@RequiresApi(Build.VERSION_CODES.P)
@Composable
@MyWeatherPreview
private fun Preview() = MyWeatherPreview {
    MainScreen()
}
