package com.alex.myweather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.alex.myweather.ui.main_screen.screen.MainScreen
import com.alex.myweather.ui.theme.MyWeatherTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyWeatherTheme {
                // A surface container using the 'background' color from the theme
                MainScreen()

            }
        }
    }
}
