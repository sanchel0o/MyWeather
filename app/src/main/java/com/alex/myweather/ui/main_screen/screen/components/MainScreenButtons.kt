package com.alex.myweather.ui.main_screen.screen.components

import android.graphics.drawable.VectorDrawable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.alex.myweather.R

@Composable
fun MenuFloatingButton(
    onClick: () -> Unit
) {
    FloatingActionButton(
        containerColor = Color.Transparent,
        onClick = { onClick() }
    ) {
        Icon(imageVector = Icons.Outlined.Menu, contentDescription = null)
    }
}