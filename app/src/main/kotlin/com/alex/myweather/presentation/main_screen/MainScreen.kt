package com.alex.myweather.presentation.main_screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alex.myweather.R
import com.alex.myweather.core.ui_utils.preview.MyWeatherPreview
import com.alex.myweather.core.ui_utils.theme.MEDIUM_PADDING
import com.alex.myweather.presentation.main_screen.composable.CurrentWeatherData
import com.alex.myweather.presentation.main_screen.composable.HourlyWeatherCard
import com.alex.myweather.presentation.main_screen.composable.MainScreenTopAppBar
import com.alex.myweather.presentation.main_screen.composable.RequestPermissions
import com.alex.myweather.presentation.main_screen.composable.WeatherInfoCard
import com.alex.myweather.presentation.main_screen.composable.dayOfMonth
import com.alex.myweather.presentation.main_screen.xml.DailyForecastView

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

    RequestPermissions {
        viewModel.onEvent(MainScreenEvents.PermissionChanged)

        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .pullRefresh(state = refreshState),
            topBar = {
                MainScreenTopAppBar(
                    onUpdateButtonClick = { viewModel.onEvent(MainScreenEvents.Refresh) }
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

                    // XML-layout item
                    item {
                        DailyForecastView(data = mainScreenState.dailyWeatherData)
                    }

//                    item {
//                        DailyForecastCard {
//                            Column {
//                                mainScreenState.dailyWeatherData.forEach { item ->
//                                    SingleDayForecast(
//                                        date = item.day,
//                                        dayHumidity = item.humidity,
//                                        imageUrl = item.imageUrl,
//                                        maxDayTemperature = item.maxTemp,
//                                        minDayTemperature = item.minTemp,
//                                        degreeUnit = stringResource(id = R.string.degree_symbol)
//                                    )
//                                }
//                            }
//                        }
//                    }
                }

                PullRefreshIndicator(
                    modifier = Modifier
                        .padding(paddingValues)
                        .align(TopCenter),
                    refreshing = mainScreenState.isRefreshing,
                    state = refreshState
                )
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
