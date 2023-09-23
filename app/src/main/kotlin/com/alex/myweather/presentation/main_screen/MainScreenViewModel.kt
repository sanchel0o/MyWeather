package com.alex.myweather.presentation.main_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alex.myweather.domain.model.CurrentWeatherData
import com.alex.myweather.domain.model.DailyWeatherData
import com.alex.myweather.domain.model.HourlyWeatherData
import com.alex.myweather.domain.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel() {

    private val _currentWeatherState = MutableStateFlow<CurrentWeatherData?>(null)
    val currentWeatherState = _currentWeatherState.asStateFlow()

    private val _hourlyWeatherState = MutableStateFlow<List<HourlyWeatherData>>(emptyList())
    val hourlyWeatherState = _hourlyWeatherState.asStateFlow()

    private val _dailyWeatherState = MutableStateFlow<List<DailyWeatherData>>(emptyList())
    val dailyWeatherState = _dailyWeatherState.asStateFlow()

    private fun getWeatherData() {

    }

    init {
        //getWeatherData()
    }
}