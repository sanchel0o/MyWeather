package com.alex.myweather.presentation.main_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alex.myweather.domain.model.CurrentWeatherData
import com.alex.myweather.domain.model.DailyWeatherData
import com.alex.myweather.domain.model.HourlyWeatherData
import com.alex.myweather.domain.repository.LocalWeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    repository: LocalWeatherRepository
) : ViewModel() {

    val currentWeatherDataFlow: StateFlow<CurrentWeatherData?> = repository
        .observeCurrentWeatherData()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = null
        )

    val dailyWeatherDataFlow: StateFlow<List<DailyWeatherData>> = repository
        .observeDailyWeatherData()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = emptyList()
        )

    val hourlyWeatherDataFlow: StateFlow<List<HourlyWeatherData>> = repository
        .observeHourlyWeatherData()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = emptyList()
        )
}
