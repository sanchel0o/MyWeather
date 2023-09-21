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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.alex.myweather.R

@Composable
fun WeatherInfoCard() {

    val rowVerticalAlignment: Alignment.Vertical = Alignment.CenterVertically

    val rowValuesPadding: Modifier = Modifier.padding(start = SMALL_PADDING.dp)

    val textStyle: TextStyle = MaterialTheme.typography.labelMedium
    val fontColor: Color = MaterialTheme.colorScheme.primary

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
            Row(
                verticalAlignment = rowVerticalAlignment
            ) {
                Icon(
                    modifier = Modifier.size(WEATHER_INFO_ICON_SIZE.dp),
                    painter = painterResource(id = R.drawable.ic_rainy),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
                Text(
                    modifier = rowValuesPadding,
                    color = fontColor,
                    style = textStyle,
                    text = "2 " + stringResource(id = R.string.percent_symbol)
                )
            }

            Row(
                verticalAlignment = rowVerticalAlignment
            ) {
                Icon(
                    modifier = Modifier.size(WEATHER_INFO_ICON_SIZE.dp),
                    painter = painterResource(id = R.drawable.ic_pressure),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
                Text(
                    modifier = rowValuesPadding,
                    color = fontColor,
                    style = textStyle,
                    text = "90 " + stringResource(id = R.string.percent_symbol)
                )
            }

            Row(
                verticalAlignment = rowVerticalAlignment
            ) {
                Icon(
                    modifier = Modifier.size(WEATHER_INFO_ICON_SIZE.dp) ,
                    painter = painterResource(id = R.drawable.ic_wind),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
                Text(
                    modifier = rowValuesPadding,
                    color = fontColor,
                    style = textStyle,
                    text = "12 " + stringResource(id = R.string.wind_speed)
                )
            }
        }
    }
}