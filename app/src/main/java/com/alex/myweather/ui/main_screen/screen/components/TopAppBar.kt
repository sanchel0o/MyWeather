package com.alex.myweather.ui.main_screen.screen.components

import androidx.compose.foundation.Indication
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenTopAppBar(
    onMenuButtonClick: () -> Unit,
    onAddCityButtonClick: () -> Unit
) {
    TopAppBar(
        navigationIcon = {
            MenuButton(onClick = onMenuButtonClick)
        },
        title = { },
        actions = {
            AddCityButton(onClick = onAddCityButtonClick)
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
fun AddCityButton(
    onClick: () -> Unit
) {
    IconButton(
        modifier = Modifier.padding(end = SMALL_PADDING.dp),
        onClick = { onClick() },
    ) {
        Icon(
            imageVector = Icons.Outlined.Add,
            tint = MaterialTheme.colorScheme.secondary,
            contentDescription = null
        )
    }
}