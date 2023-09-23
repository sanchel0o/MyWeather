package com.alex.myweather.core.ui_utils.preview

import android.content.res.Configuration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.alex.myweather.core.ui_utils.theme.DarkColorScheme
import com.alex.myweather.core.ui_utils.theme.LightColorScheme
import com.alex.myweather.core.ui_utils.theme.Typography

@Preview(
    group = "Light",
    showBackground = false,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "Light",
)
@Preview(
    group = "Night",
    showBackground = false,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Night",
)
annotation class MyWeatherPreview

@Composable
fun MyWeatherPreview(
    content: @Composable () -> Unit = { }
) {
    val colorScheme = when {
        isSystemInDarkTheme() -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
