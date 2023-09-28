package com.alex.myweather.presentation.main_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alex.myweather.core.coroutine_utils.mutableStateIn
import com.alex.myweather.domain.repository.LocalWeatherRepository
import com.alex.myweather.domain.repository.RemoteWeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val CITY = "Penza"
private const val DAYS_QUANTITY = 5

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val remoteRepository: RemoteWeatherRepository,
    private val localRepository: LocalWeatherRepository,
) : ViewModel() {

    private val _mainScreenState: MutableStateFlow<MainScreenState> = combine(
        localRepository.observeCurrentWeatherData(),
        localRepository.observeDailyWeatherData(),
        localRepository.observeHourlyWeatherData(),
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

    fun onEvent(event: MainScreenEvents) {
        when (event) {
            MainScreenEvents.PermissionChanged -> {
                _mainScreenState.value = _mainScreenState.value.copy(
                    foregroundServicePermission = true
                )
            }

            MainScreenEvents.Refresh -> {
                viewModelScope.launch {
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
    }

    private suspend fun loadWeatherData() {
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

    init {
        viewModelScope.launch {
            loadWeatherData()
        }
    }
}
