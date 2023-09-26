package com.alex.myweather.domain.di

import com.alex.myweather.data.repository.RemoteWeatherRepositoryImpl
import com.alex.myweather.domain.repository.RemoteWeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class WeatherRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRemoteWeatherRepository(
        weatherRemoteRepositoryImpl: RemoteWeatherRepositoryImpl
    ): RemoteWeatherRepository
}