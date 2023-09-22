package com.alex.myweather.ui.main_screen.screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.alex.myweather.R

@Composable
fun HourlyWeatherCard() {

    Card(
        modifier = Modifier,
    ) {
        Column(
            modifier = Modifier.padding(MEDIUM_PADDING.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "12:00") //time
            Icon(
                modifier = Modifier
                    .size(WEATHER_INFO_LARGE_ICON_SIZE.dp)
                    .padding(top = SMALL_PADDING.dp, bottom = SMALL_PADDING.dp),
                painter = painterResource(id = R.drawable.ic_rainshower),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
            Text(text = "15 " + stringResource(id = R.string.degree_symbol))
        }

    }
}