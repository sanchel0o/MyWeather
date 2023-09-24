package com.alex.myweather.presentation.main_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alex.myweather.domain.model.CurrentWeatherData
import com.alex.myweather.domain.model.DailyWeatherData
import com.alex.myweather.domain.model.ForecastData
import com.alex.myweather.domain.repository.LocalWeatherRepository
import com.alex.myweather.domain.repository.RemoteWeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val repository: LocalWeatherRepository
) : ViewModel() {

    val currentWeatherDataFlow : StateFlow<CurrentWeatherData?> = repository
        .observeCurrentWeatherData()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = null
        )

    val dailyWeatherDataFlow : StateFlow<DailyWeatherData?> = repository
        .observeDailyWeatherData()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = null
        )

//    private fun getWeatherData() {
//        viewModelScope.launch {
//            val result = try {
//
//                _forecastDataState.value = _forecastDataState.value.copy(
//                    isLoading = true,
//                )
//
//                repository.loadForecastData()
//            } catch (e: Exception) {
//                Log.d("AAA", "Exception code is: ${e.message}")
//                null
//            }
//
//            _forecastDataState.value = _forecastDataState.value.copy(
//                dailyWeatherData = result?.dailyWeatherData ?: emptyList(),
//                hourlyWeatherData = result?.hourlyWeatherData ?: emptyList(),
//                currentWeatherData = result?.currentWeatherData,
//                isLoading = false
//            )
//        }
//    }
}
