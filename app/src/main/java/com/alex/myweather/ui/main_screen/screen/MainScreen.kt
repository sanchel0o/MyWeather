package com.alex.myweather.ui.main_screen.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alex.myweather.R
import com.alex.myweather.ui.main_screen.screen.components.CurrentWeatherData
import com.alex.myweather.ui.main_screen.screen.components.DailyForecastDisplay
import com.alex.myweather.ui.main_screen.screen.components.HourlyWeatherCard
import com.alex.myweather.ui.main_screen.screen.components.MEDIUM_PADDING
import com.alex.myweather.ui.main_screen.screen.components.MainScreenTopAppBar
import com.alex.myweather.ui.main_screen.screen.components.WeatherInfoCard

@Composable
fun MainScreen() {

//    val padding = with(LocalDensity.current) { // Access the current density
//        val screenWidth = LocalConfiguration.current.screenWidthDp
//        val screenHeight = LocalConfiguration.current.screenHeightDp
//
//        // Calculate padding values based on screen size
//        val horizontalPadding = screenWidth.dp.div(20)
//        val verticalPadding = screenHeight.dp.div(10)
//
//        // Return PaddingValues object with calculated values
//        PaddingValues(horizontal = horizontalPadding, vertical = verticalPadding)
//    }


    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),

        topBar = {
            MainScreenTopAppBar(
                onMenuButtonClick = {},
                onAddCityButtonClick = {}
            )
        }
    ) { paddingValues ->
        Box(
            Modifier.fillMaxSize().padding(paddingValues),
            //contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                CurrentWeatherData(
                    icon = painterResource(id = R.drawable.ic_cloudy),
                    currentTemperature = 50,
                    unit = stringResource(id = R.string.degree_symbol),
                    maxTemperature = 60,
                    minTemperature = 10,
                )

                WeatherInfoCard(
                    pressure = 90,
                    humidity = 25,
                    windSpeed = 5
                )

                LazyRow(
                    modifier = Modifier
                        .padding(horizontal = MEDIUM_PADDING.dp)
                        .fillMaxWidth(),
                    //contentPadding = PaddingValues(horizontal = SMALL_PADDING.dp),
                    horizontalArrangement = Arrangement.spacedBy(space = 12.dp)
                ) {
                    items(24) {
                        HourlyWeatherCard(
                            temperature = 25,
                            unit = stringResource(id = R.string.degree_symbol),
                            icon = painterResource(id = R.drawable.ic_rainy)
                        )
                    }
                }

                DailyForecastDisplay(
                    dayName = "Monday",
                    dayHumidity = 14,
                    maxDayTemperature = 25,
                    minDayTemperature = 15,
                    unit = stringResource(id = R.string.degree_symbol),
                    weatherIcon = painterResource(id = R.drawable.ic_cloudy)
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun MainScreenPreview() {
    MainScreen()
}
