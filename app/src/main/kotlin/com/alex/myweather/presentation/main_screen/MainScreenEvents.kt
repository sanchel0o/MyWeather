package com.alex.myweather.presentation.main_screen

sealed class MainScreenEvents {
    data object PermissionChanged: MainScreenEvents()
    data object Refresh: MainScreenEvents()
}
