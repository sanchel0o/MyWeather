package com.alex.myweather.ui.main_screen.screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun CurrentWeatherData(
    icon: Painter,
    unit: String,
    currentTemperature: Int,
    maxTemperature: Int,
    minTemperature: Int
) {
    Column(
        modifier = Modifier
            .padding(
                horizontal = MEDIUM_PADDING.dp,
                vertical = SMALL_PADDING.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CurrentWeatherIcon(icon = icon)

        CurrentTemperature(
            currentTemperature = currentTemperature,
            unit = unit
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            MaxMinTemperature(
                ext = "Max",
                temp = maxTemperature,
                unit = unit
            )
            MaxMinTemperature(
                ext = "Min",
                temp = minTemperature,
                unit = unit
            )
        }
    }

}

@Composable
fun CurrentWeatherIcon(
    icon: Painter,
) {
    Icon(
        //modifier = Modifier.sizeIn(maxHeight = 200.dp),

        //tint = MaterialTheme.colorScheme.primary,
        painter = icon,
        contentDescription = null
    )
}

@Composable
fun CurrentTemperature(
    currentTemperature: Int,
    unit: String
) {
    Text(
        style = MaterialTheme.typography.labelLarge,
        color = MaterialTheme.colorScheme.primary,
        text = currentTemperature.toString() + unit
    )
}

@Composable
fun DayMonth() {

}

@Composable
fun MaxMinTemperature(
    ext: String,
    temp: Int,
    unit: String
) {
    Text(
        color = MaterialTheme.colorScheme.secondary,
        style = MaterialTheme.typography.titleMedium,
        text = "$ext: $temp$unit"
    )
}
