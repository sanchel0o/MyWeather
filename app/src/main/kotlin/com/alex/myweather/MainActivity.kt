package com.alex.myweather

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.alex.myweather.core.ui_utils.theme.MyWeatherTheme
import com.alex.myweather.presentation.main_screen.MainScreen
import com.alex.myweather.service.WeatherService
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        startWeatherService()

        setContent {
            MyWeatherTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }

    private fun startWeatherService() {
        val intent = Intent(this, WeatherService::class.java)
        startForegroundService(intent)
    }
}
