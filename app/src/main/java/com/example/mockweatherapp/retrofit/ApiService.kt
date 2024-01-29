package com.example.mockweatherapp.retrofit

import com.example.mockweatherapp.model.WeatherDataResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("endpoint")
    fun getData(): Response<WeatherDataResponse>
}