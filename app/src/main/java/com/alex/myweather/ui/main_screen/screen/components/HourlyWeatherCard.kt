package com.alex.myweather.ui.main_screen.screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.alex.myweather.R

@Composable
fun HourlyWeatherCard(
    temperature: Int,
    unit: String,
    icon: Painter,
) {
    Column(
        modifier = Modifier.padding(SMALL_PADDING.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.outline,
            text = "12:00"
        ) //time
        Icon(
            modifier = Modifier
                .size(WEATHER_INFO_LARGE_ICON_SIZE.dp)
                .padding(top = SMALL_PADDING.dp, bottom = SMALL_PADDING.dp),
            painter = icon,
            contentDescription = null
        )
        Text(
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.primary,
            text = temperature.toString() + unit
        )
    }
}