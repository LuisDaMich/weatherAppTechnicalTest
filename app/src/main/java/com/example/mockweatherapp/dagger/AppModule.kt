package com.example.mockweatherapp.dagger

import com.example.mockweatherapp.viewModel.ShowWeatherViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideShowWeatherViewModel(): ShowWeatherViewModel{
        return ShowWeatherViewModel()
    }
}