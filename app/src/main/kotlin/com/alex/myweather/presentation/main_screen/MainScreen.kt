package com.alex.myweather.presentation.main_screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.pullRefreshIndicatorTransform
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
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
import com.alex.myweather.presentation.main_screen.components.RequestPermissions
import com.alex.myweather.presentation.main_screen.components.SingleDayForecast
import com.alex.myweather.presentation.main_screen.components.WeatherInfoCard
import com.alex.myweather.presentation.main_screen.components.dayOfMonth

@OptIn(ExperimentalMaterialApi::class)
@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = hiltViewModel()
) {
    val mainScreenState by viewModel.mainScreenState.collectAsStateWithLifecycle()

    val refreshState = rememberPullRefreshState(
        refreshing = mainScreenState.isRefreshing,
        onRefresh = {
            viewModel.onEvent(MainScreenEvents.Refresh)
        }
    )
    val rotation = animateFloatAsState(refreshState.progress * 120, label = "")

    RequestPermissions {
        viewModel.onEvent(MainScreenEvents.PermissionChanged)

        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .pullRefresh(state = refreshState),
            topBar = {
                MainScreenTopAppBar(
                    onMenuButtonClick = { },
                    onAddCityButtonClick = { }
                )
            }
        ) { paddingValues ->
            Box {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    item {
                        CurrentWeatherData(
                            imageUrl = mainScreenState.currentWeatherData?.imageUrl ?: "",
                            currentTemperature = mainScreenState.currentWeatherData?.temperature,
                            unit = stringResource(id = R.string.degree_symbol),
                            currentCondition = mainScreenState.currentWeatherData?.condition,
                            date = dayOfMonth
                        )
                    }

                    item {
                        WeatherInfoCard(
                            pressure = mainScreenState.currentWeatherData?.pressure,
                            humidity = mainScreenState.currentWeatherData?.humidity,
                            windSpeed = mainScreenState.currentWeatherData?.windSpeed
                        )
                    }

                    item {
                        LazyRow(
                            modifier = Modifier
                                .padding(horizontal = MEDIUM_PADDING.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(space = 12.dp),

                            ) {
                            items(mainScreenState.hourlyWeatherData) { item ->
                                HourlyWeatherCard(
                                    time = item.time,
                                    temperature = item.temperature,
                                    unit = stringResource(id = R.string.degree_symbol),
                                    imageUrl = item.imageUrl
                                )
                            }
                        }
                    }

                    item {
                        DailyForecastCard {
                            Column {
                                mainScreenState.dailyWeatherData.forEach { item ->
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
                Surface(
                    modifier = Modifier
                        .size(40.dp)
                        .align(TopCenter)
                        .pullRefreshIndicatorTransform(refreshState)
                        .rotate(rotation.value),
                    color = MaterialTheme.colorScheme.tertiary,
                    shape = CircleShape,
                    elevation = if (refreshState.progress > 0 || mainScreenState.isRefreshing) 20.dp else 0.dp,
                ) {
                    Icon(
                        tint = MaterialTheme.colorScheme.onTertiary,
                        imageVector = Icons.Filled.Refresh,
                        contentDescription = null)
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
