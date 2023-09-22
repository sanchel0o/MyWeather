package com.alex.myweather.ui.main_screen.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alex.myweather.ui.main_screen.screen.components.HourlyWeatherCard
import com.alex.myweather.ui.main_screen.screen.components.MEDIUM_PADDING
import com.alex.myweather.ui.main_screen.screen.components.WeatherInfoCard

@Composable
fun MainScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {
        Column {
            WeatherInfoCard(
                pressure = 90,
                humidity = 25,
                windSpeed = 5
            )

            LazyRow(
                modifier = Modifier
                    .padding(start = MEDIUM_PADDING.dp, end = MEDIUM_PADDING.dp)
                    .fillMaxWidth(),
                //contentPadding = paddingValues,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                items(24) {
                    HourlyWeatherCard()
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun MainScreenPreview() {
    MainScreen()
}
