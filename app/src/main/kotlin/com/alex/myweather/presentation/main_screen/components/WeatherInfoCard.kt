package com.alex.myweather.presentation.main_screen.components

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
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.alex.myweather.R
import com.alex.myweather.core.ui_utils.preview.MyWeatherPreview
import com.alex.myweather.core.ui_utils.theme.LARGE_PADDING
import com.alex.myweather.core.ui_utils.theme.MEDIUM_CORNER_RADIUS
import com.alex.myweather.core.ui_utils.theme.MEDIUM_PADDING
import com.alex.myweather.core.ui_utils.theme.SMALL_PADDING
import com.alex.myweather.core.ui_utils.theme.WEATHER_INFO_MEDIUM_ICON_SIZE

@Composable
@MyWeatherPreview
private fun Preview() = MyWeatherPreview {
    WeatherInfoCard(
        pressure = 12,
        humidity = 24,
        windSpeed = 34
    )
}

@Composable
fun WeatherInfoCard(
    pressure: Int?,
    humidity: Int?,
    windSpeed: Int?
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = MEDIUM_PADDING.dp,
                vertical = SMALL_PADDING.dp
            )
            .shadow(
                shape = RoundedCornerShape(MEDIUM_CORNER_RADIUS.dp),
                elevation = 24.dp,
                spotColor = MaterialTheme.colorScheme.primary,
            ),
        shape = RoundedCornerShape(MEDIUM_CORNER_RADIUS.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = LARGE_PADDING.dp,
                    vertical = SMALL_PADDING.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            WeatherInfo(
                valueType = pressure,
                unit = stringResource(id = R.string.percent_symbol),
                icon = painterResource(id = R.drawable.ic_pressure)
            )

            WeatherInfo(
                valueType = humidity,
                unit = stringResource(id = R.string.percent_symbol),
                icon = painterResource(id = R.drawable.ic_drop)
            )

            WeatherInfo(
                valueType = windSpeed,
                unit = stringResource(id = R.string.wind_speed),
                icon = painterResource(id = R.drawable.ic_wind)
            )
        }
    }
}

@Composable
fun WeatherInfo(
    valueType: Int?,
    unit: String,
    icon: Painter
) {
    val value by remember(valueType) {
        derivedStateOf { 
            if (valueType != null) {
                valueType.toString() + unit
            } else {
                "?"
            }
        }
    }

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(WEATHER_INFO_MEDIUM_ICON_SIZE.dp),
            painter = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )
        Text(
            modifier = Modifier.padding(start = SMALL_PADDING.dp),
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.labelMedium,
            text = value
        )
    }
}