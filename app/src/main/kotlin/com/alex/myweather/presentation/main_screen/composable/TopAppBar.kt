package com.alex.myweather.presentation.main_screen.composable

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alex.myweather.core.ui_utils.preview.MyWeatherPreview
import com.alex.myweather.core.ui_utils.theme.SMALL_PADDING

@Composable
@MyWeatherPreview
private fun Preview() = MyWeatherPreview {
    MainScreenTopAppBar(
        onMenuButtonClick = {},
        onUpdateButtonClick = {}
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenTopAppBar(
    onMenuButtonClick: () -> Unit = {},
    onUpdateButtonClick: () -> Unit = {}
) {
    TopAppBar(
        navigationIcon = {
            MenuButton(onClick = onMenuButtonClick)
        },
        title = { },
        actions = {
            UpdateButton(onClick = onUpdateButtonClick)
        }
    )
}

@Composable
fun MenuButton(
    onClick: () -> Unit
) {
    IconButton(
        modifier = Modifier.padding(start = SMALL_PADDING.dp),
        onClick = { onClick() },
    ) {
        Icon(
            imageVector = Icons.Outlined.Menu,
            tint = MaterialTheme.colorScheme.primary,
            contentDescription = null
        )
    }
}

@Composable
fun UpdateButton(
    onClick: () -> Unit
) {
    IconButton(
        modifier = Modifier.padding(end = SMALL_PADDING.dp),
        onClick = { onClick() },
    ) {
        Icon(
            imageVector = Icons.Outlined.Refresh,
            tint = MaterialTheme.colorScheme.secondary,
            contentDescription = null
        )
    }
}
