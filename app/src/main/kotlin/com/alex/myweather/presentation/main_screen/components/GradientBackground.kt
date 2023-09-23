package com.alex.myweather.presentation.main_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.alex.myweather.core.ui_utils.theme.backgroundGradientDark
import com.alex.myweather.core.ui_utils.theme.backgroundGradientLight

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
