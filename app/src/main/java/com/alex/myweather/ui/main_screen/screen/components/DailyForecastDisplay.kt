package com.alex.myweather.ui.main_screen.screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
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
fun DailyForecastDisplay(
    dayName: String,
    dayHumidity: Int,
    weatherIcon: Painter,
    maxDayTemperature: Int,
    minDayTemperature: Int,
    unit: String
) {
    Card(
        modifier = Modifier
            .padding(
                horizontal = MEDIUM_PADDING.dp,
                vertical = SMALL_PADDING.dp)
            .shadow(
                shape = RoundedCornerShape(MEDIUM_CORNER_RADIUS.dp),
                elevation = 24.dp,
                spotColor = MaterialTheme.colorScheme.primary,
            ),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(MEDIUM_CORNER_RADIUS.dp)
    ) {
        for (i in 1..7) {
            SingleDayForecast(
                dayName = dayName,
                dayHumidity = dayHumidity,
                weatherIcon = weatherIcon,
                maxDayTemperature = maxDayTemperature,
                minDayTemperature = minDayTemperature,
                unit = unit
            )
        }
    }
}

@Composable
fun SingleDayForecast(
    dayName: String,
    dayHumidity: Int,
    weatherIcon: Painter,
    maxDayTemperature: Int,
    minDayTemperature: Int,
    unit: String
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = MEDIUM_PADDING.dp,
                    vertical = SMALL_PADDING.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                color = MaterialTheme.colorScheme.primary,
                text = dayName
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    modifier = Modifier.size(WEATHER_INFO_MEDIUM_ICON_SIZE.dp),
                    tint = MaterialTheme.colorScheme.primary,
                    painter = painterResource(id = R.drawable.ic_drop),
                    contentDescription = null
                )

                Text(
                    modifier = Modifier,
                    color = MaterialTheme.colorScheme.tertiary,
                    text = dayHumidity.toString() + stringResource(id = R.string.percent_symbol)
                )
            }

            Icon(
                modifier = Modifier.size(WEATHER_INFO_MEDIUM_ICON_SIZE.dp),
                painter = weatherIcon,
                contentDescription = null
            )

            Text(
                text = "$maxDayTemperature$unit/ $minDayTemperature$unit"
            )
        }
    }
}

