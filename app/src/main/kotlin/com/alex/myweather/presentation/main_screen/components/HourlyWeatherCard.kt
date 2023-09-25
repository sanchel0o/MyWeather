package com.alex.myweather.presentation.main_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.alex.myweather.R
import com.alex.myweather.core.ui_utils.preview.MyWeatherPreview
import com.alex.myweather.core.ui_utils.theme.SMALL_PADDING
import com.alex.myweather.core.ui_utils.theme.WEATHER_INFO_LARGE_ICON_SIZE

@Composable
@MyWeatherPreview
private fun Preview() = MyWeatherPreview {
    HourlyWeatherCard(
        time = "12:00",
        temperature = 15,
        unit = stringResource(id = R.string.degree_symbol),
        imageUrl = "http://cdn.weatherapi.com/weather/64x64/day/116.png"
    )
}

@Composable
fun HourlyWeatherCard(
    time: String,
    temperature: Int,
    unit: String,
    imageUrl: String,
) {
    Column(
        modifier = Modifier.padding(SMALL_PADDING.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.outline,
            text = time
        )

        AsyncImage(
            modifier = Modifier
                .size(WEATHER_INFO_LARGE_ICON_SIZE.dp)
                .padding(vertical = SMALL_PADDING.dp)
                .aspectRatio(1f),
            contentScale = ContentScale.FillHeight,
            model = imageUrl,
            contentDescription = null
        )

        Text(
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.primary,
            text = temperature.toString() + unit
        )
    }
}