package com.alex.myweather.ui.main_screen.screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.alex.myweather.R

@Composable
fun WeatherInfoCard(
    pressure: Int,
    humidity: Int,
    windSpeed: Int
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MEDIUM_PADDING.dp)
            .shadow(
                shape = RoundedCornerShape(MEDIUM_CORNER_RADIUS.dp),
                elevation = 24.dp,
                spotColor = Color.Blue,
            ),
        shape = RoundedCornerShape(MEDIUM_CORNER_RADIUS.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = LARGE_PADDING.dp,
                    end = LARGE_PADDING.dp,
                    top = SMALL_PADDING.dp,
                    bottom = SMALL_PADDING.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            WeatherInfo(
                value = pressure,
                unit = stringResource(id = R.string.percent_symbol),
                icon = painterResource(id = R.drawable.ic_pressure)
            )

            WeatherInfo(
                value = humidity,
                unit = stringResource(id = R.string.percent_symbol),
                icon = painterResource(id = R.drawable.ic_drop)
            )

            WeatherInfo(
                value = windSpeed,
                unit = stringResource(id = R.string.wind_speed),
                icon = painterResource(id = R.drawable.ic_wind)
            )
        }
    }
}

@Composable
fun WeatherInfo(
    value: Int,
    unit: String,
    icon: Painter
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(WEATHER_INFO_LARGE_ICON_SIZE.dp),
            painter = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )
        Text(
            modifier = Modifier.padding(start = SMALL_PADDING.dp),
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.labelMedium,
            text = value.toString() + unit
        )
    }
}