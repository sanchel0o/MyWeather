package com.alex.myweather.presentation.main_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alex.myweather.domain.model.ForecastData
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

    private val _forecastDataState = MutableStateFlow(ForecastData())
    val forecastDataState = _forecastDataState.asStateFlow()

    private fun getWeatherData() {
        viewModelScope.launch {
            val result = try {

                _forecastDataState.value = _forecastDataState.value.copy(
                    isLoading = true,
                )

                repository.getData()
            } catch (e: Exception) {
                Log.d("AAA", "Exception code is: ${e.message}")
                null
            }

            _forecastDataState.value = _forecastDataState.value.copy(
                dailyWeatherData = result?.dailyWeatherData ?: emptyList(),
                hourlyWeatherData = result?.hourlyWeatherData ?: emptyList(),
                currentWeatherData = result?.currentWeatherData,
                isLoading = false
            )
        }

    }

    init {
        getWeatherData()
    }
}
