package com.alex.myweather.presentation.main_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alex.myweather.domain.model.CurrentWeatherData
import com.alex.myweather.domain.model.DailyWeatherData
import com.alex.myweather.domain.model.HourlyWeatherData
import com.alex.myweather.domain.repository.LocalWeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    repository: LocalWeatherRepository
) : ViewModel() {

    private val _mainScreenState = MutableStateFlow(MainScreenState())
    val mainScreenState = _mainScreenState.asStateFlow()

    fun onEvent(event: MainScreenEvents) {
        when (event) {
            MainScreenEvents.PermissionChanged -> {
                _mainScreenState.value = _mainScreenState.value.copy(
                    foregroundServicePermission = true
                )
            }
        }
    }

    fun loadData() {
        viewModelScope.launch {

            _mainScreenState.value = _mainScreenState.value.copy(
                currentWeatherData = currentWeatherDataFlow.value,
                dailyWeatherData = dailyWeatherDataFlow.value,
                hourlyWeatherData = hourlyWeatherDataFlow.value
            )

        }
    }

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

    private val _state: MutableStateFlow<MainScreenState> =
        combine(
            currentWeatherDataFlow,
            dailyWeatherDataFlow,
            hourlyWeatherDataFlow
        ) { currentWeatherData, dailyWeatherData, hourlyWeatherData ->
            MainScreenState(
                foregroundServicePermission = true,
                currentWeatherData = currentWeatherData,
                dailyWeatherData = dailyWeatherData,
                hourlyWeatherData = hourlyWeatherData
            )
        }.mutableStateIn(
            scope = viewModelScope,
            initialValue = MainScreenState())
    val state = _state.asStateFlow()

    private fun <T> Flow<T>.mutableStateIn(
        scope: CoroutineScope,
        initialValue: T
    ): MutableStateFlow<T> {
        val flow = MutableStateFlow(initialValue)

        scope.launch {
            this@mutableStateIn.collect(flow)
        }

        return flow
    }
}
