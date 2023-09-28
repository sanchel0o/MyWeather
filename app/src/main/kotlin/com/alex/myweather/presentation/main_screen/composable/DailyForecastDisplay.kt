package com.alex.myweather.presentation.main_screen.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.alex.myweather.R
import com.alex.myweather.core.ui_utils.preview.MyWeatherPreview
import com.alex.myweather.core.ui_utils.theme.MEDIUM_CORNER_RADIUS
import com.alex.myweather.core.ui_utils.theme.MEDIUM_PADDING
import com.alex.myweather.core.ui_utils.theme.SMALL_PADDING
import com.alex.myweather.core.ui_utils.theme.WEATHER_INFO_MEDIUM_ICON_SIZE
import com.alex.myweather.core.ui_utils.theme.WEATHER_INFO_SMALL_ICON_SIZE
import java.time.LocalDateTime

@Composable
@MyWeatherPreview
private fun Preview() = MyWeatherPreview {
    DailyForecastCard()
    {
        SingleDayForecast(
            date = LocalDateTime.now().dayOfWeek.toString(),
            dayHumidity = 23,
            imageUrl = "http://cdn.weatherapi.com/weather/64x64/day/116.png",
            maxDayTemperature = 27,
            minDayTemperature = 15,
            degreeUnit = stringResource(id = R.string.degree_symbol)
        )
        SingleDayForecast(
            date = "Wednesday",
            dayHumidity = 23,
            imageUrl = "http://cdn.weatherapi.com/weather/64x64/day/116.png",
            maxDayTemperature = 27,
            minDayTemperature = 15,
            degreeUnit = stringResource(id = R.string.degree_symbol)
        )
    }
}

@Composable
fun DailyForecastCard(
    singleDayForecast: @Composable () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(
                horizontal = MEDIUM_PADDING.dp,
                vertical = SMALL_PADDING.dp
            )
            .shadow(
                shape = RoundedCornerShape(MEDIUM_CORNER_RADIUS.dp),
                elevation = 24.dp,
                spotColor = MaterialTheme.colorScheme.primary,
            ),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(MEDIUM_CORNER_RADIUS.dp)
    ) {
        singleDayForecast()
    }
}

@Composable
fun SingleDayForecast(
    date: String,
    dayHumidity: Int,
    imageUrl: String,
    maxDayTemperature: Int,
    minDayTemperature: Int,
    degreeUnit: String
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = MEDIUM_PADDING.dp,
                    vertical = SMALL_PADDING.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    color = MaterialTheme.colorScheme.primary,
                    text = date.lowercase().capitalize()
                )
            }

            Row(
                modifier = Modifier.padding(end = MEDIUM_PADDING.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    modifier = Modifier.size(WEATHER_INFO_SMALL_ICON_SIZE.dp),
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

            Row(
                modifier = Modifier.padding(start = MEDIUM_PADDING.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                AsyncImage(
                    modifier = Modifier.size(WEATHER_INFO_MEDIUM_ICON_SIZE.dp),
                    model = imageUrl,
                    contentDescription = null
                )
            }

            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "$maxDayTemperature$degreeUnit/ $minDayTemperature$degreeUnit"
                )
            }
        }
    }
}
