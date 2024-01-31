package com.example.mockweatherapp.dagger

import com.example.mockweatherapp.MainActivity
import dagger.Component
import dagger.hilt.DefineComponent
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
}