package com.example.mockweatherapp.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mockweatherapp.mockServer.MockService
import com.example.mockweatherapp.model.WeatherDataResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowWeatherViewModel @Inject constructor(): ViewModel() {
    private val mockService = MockService()
    fun fetchDataFromApi(onSuccess: (WeatherDataResponse) -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                mockService.startMockWebServer()
                val response = mockService.createApiService().getData()
                if (response.isSuccessful) {
                    val data = response.body()
                    data?.let {
                        onSuccess(it)
                    } ?: onError("Data null")
                } else {
                    Log.e("else", "entra")
                    onError("API call failed with code: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("catch", e.toString())
                onSuccess(mockService.buildWeatherDataResponse())
            }
        }
    }
}