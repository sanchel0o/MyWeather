package com.alex.myweather.ui.main_screen.screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.alex.myweather.ui.theme.backgroundGradientDark
import com.alex.myweather.ui.theme.backgroundGradientLight

@Composable
fun GradientBackground(content: @Composable () -> Unit) {

    val backgroundColor = if(isSystemInDarkTheme()) backgroundGradientDark else backgroundGradientLight
    Box(modifier = Modifier
        .fillMaxSize()
        .background(backgroundColor)
    ) {
        content()
    }
}
