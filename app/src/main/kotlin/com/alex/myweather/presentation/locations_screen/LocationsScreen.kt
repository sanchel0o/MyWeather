package com.alex.myweather.presentation.locations_screen

import android.graphics.drawable.VectorDrawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.alex.myweather.core.ui_utils.preview.MyWeatherPreview
import com.alex.myweather.core.ui_utils.theme.LARGE_PADDING
import com.alex.myweather.core.ui_utils.theme.MEDIUM_PADDING
import com.alex.myweather.core.ui_utils.theme.SMALL_PADDING

@Composable
@MyWeatherPreview
private fun Preview() = MyWeatherPreview {
    LocationsScreen()
}

@Composable
fun LocationsScreen() {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues
        ) {
            items(5) { item ->
                CityCard(cityName = "Penza", date = "22/22/2222")
            }
        }
    }
}

@Composable
fun CityCard(
    cityName: String,
    date: String,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(SMALL_PADDING.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = SMALL_PADDING.dp,
                    end = SMALL_PADDING.dp,
                    top = LARGE_PADDING.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
//            AsyncImage(model = , contentDescription = )
            Row {
                Image(
                    imageVector = Icons.Outlined.LocationOn,
                    contentDescription = null
                )

                Text(
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary,
                    text = cityName
                )
            }

            Image(
                imageVector = Icons.Filled.Notifications,
                contentDescription = null
            )
        }
        Text(
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.outline,
            modifier = Modifier.padding(
                start = SMALL_PADDING.dp,
                bottom = LARGE_PADDING.dp
            ),
            text = date
        )
    }

}