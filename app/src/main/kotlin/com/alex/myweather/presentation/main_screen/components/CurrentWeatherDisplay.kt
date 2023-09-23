package com.alex.myweather.presentation.main_screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.alex.myweather.R
import com.alex.myweather.core.ui_utils.preview.MyWeatherPreview
import com.alex.myweather.core.ui_utils.theme.MEDIUM_PADDING
import com.alex.myweather.core.ui_utils.theme.SMALL_PADDING

@Composable
@MyWeatherPreview
private fun Preview() = MyWeatherPreview {
    CurrentWeatherData(
        imageUrl = "https://i.dummyjson.com/data/products/1/1.jpg",
        unit = stringResource(id = R.string.degree_symbol),
        currentTemperature = 50
    )
}

@Composable
fun CurrentWeatherData(
    imageUrl: String,
    unit: String,
    currentTemperature: Int?
) {
    Column(
        modifier = Modifier
            .padding(
                horizontal = MEDIUM_PADDING.dp,
                vertical = SMALL_PADDING.dp
            )
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CurrentWeatherIcon(imageUrl = imageUrl)

        CurrentTemperature(
            currentTemperature = currentTemperature,
            unit = unit
        )
    }
}

//@Composable
//fun CurrentWeatherIcon(
//    icon: Painter,
//) {
//    Icon(
//        painter = icon,
//        contentDescription = null,
//    )
//}

@Composable
fun CurrentWeatherIcon(
    imageUrl: String
){

    AsyncImage(
        contentScale = ContentScale.FillWidth,
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f),
        model = imageUrl,
        contentDescription = null
    )
}

@Composable
fun CurrentTemperature(
    currentTemperature: Int?,
    unit: String
) {
    val value by remember(currentTemperature, unit) {
        derivedStateOf {
            if(currentTemperature != null) {
                currentTemperature.toString() + unit
            } else {
                "?"
            }
        }
    }

    Text(
        style = MaterialTheme.typography.labelLarge,
        color = MaterialTheme.colorScheme.primary,
        text = value
    )
}

//TODO - make date and time of current day of the month
@Composable
fun DayMonth() {

}
