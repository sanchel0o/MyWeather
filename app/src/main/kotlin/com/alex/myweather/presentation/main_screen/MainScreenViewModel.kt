package com.alex.myweather.presentation.main_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alex.myweather.domain.model.CurrentWeatherData
import com.alex.myweather.domain.model.DailyWeatherData
import com.alex.myweather.domain.model.HourlyWeatherData
import com.alex.myweather.domain.repository.LocalWeatherRepository
import com.alex.myweather.domain.repository.RemoteWeatherRepository
import com.alex.myweather.presentation.main_screen.util.mutableStateIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val CITY = "Penza"
private const val DAYS_QUANTITY = 5

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val remoteRepository: RemoteWeatherRepository,
    private val localRepository: LocalWeatherRepository,
) : ViewModel() {

    fun onEvent(event: MainScreenEvents) {
        when (event) {
            MainScreenEvents.PermissionChanged -> {
                _mainScreenState.value = _mainScreenState.value.copy(
                    foregroundServicePermission = true
                )
            }

            MainScreenEvents.Refresh -> {
                _mainScreenState.value = _mainScreenState.value.copy(
                    isRefreshing = true
                )
                loadWeatherData()
                _mainScreenState.value = _mainScreenState.value.copy(
                    isRefreshing = false
                )
            }
        }
    }

    private fun loadWeatherData() {
        viewModelScope.launch {
            val result = try {
                remoteRepository.loadForecastData(city = CITY, days = DAYS_QUANTITY)
            } catch (e: Exception) {
                Log.d("VM", "Exception message is: ${e.message}")
                null
            }

            localRepository.apply {
                result?.currentWeatherData?.let { saveCurrentWeatherData(it) }
                result?.dailyWeatherData?.map { saveDailyWeatherData(it) }
                result?.hourlyWeatherData?.map { saveHourlyWeatherData(it) }
            }
        }
    }

    private val currentWeatherDataFlow: StateFlow<CurrentWeatherData?> = localRepository
        .observeCurrentWeatherData()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = null
        )

    private val dailyWeatherDataFlow: StateFlow<List<DailyWeatherData>> = localRepository
        .observeDailyWeatherData()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = emptyList()
        )

    private val hourlyWeatherDataFlow: StateFlow<List<HourlyWeatherData>> = localRepository
        .observeHourlyWeatherData()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = emptyList()
        )

    private val _mainScreenState: MutableStateFlow<MainScreenState> = combine(
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
        initialValue = MainScreenState()
    )

    val mainScreenState = _mainScreenState.asStateFlow()

    init {
        loadWeatherData()
    }
}
