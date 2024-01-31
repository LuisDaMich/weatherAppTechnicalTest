package com.example.mockweatherapp.mockServer

import android.util.Log
import com.example.mockweatherapp.model.Data
import com.example.mockweatherapp.model.Temperature
import com.example.mockweatherapp.model.WeatherDataResponse
import com.example.mockweatherapp.retrofit.ApiService
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class MockService {

    private val mockSWebServer = MockWebServer()
    fun startMockWebServer() {
        mockSWebServer.enqueue(MockResponse().setResponseCode(200).setBody(buildStringWeatherDataResponse()))
        mockSWebServer.start()
    }

    fun createApiService(): ApiService{
        val httpClient = OkHttpClient.Builder().build()
        val retrofit = Retrofit.Builder()
            .baseUrl(getBaseUrl())
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(ApiService::class.java)
    }

    fun shutdownMockServer() {
        mockSWebServer.shutdown()
    }

    private fun getBaseUrl(): String {
        Log.e("baseUrl", mockSWebServer.url("/").toString())
        return mockSWebServer.url("/").toString()
    }

    private fun buildStringWeatherDataResponse(): String {
        return Gson().toJson(buildWeatherDataResponse())
    }

    private fun getTimeStamp(): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX")
        val zoneId = ZoneId.of("America/Mexico_City")
        val zonedDateTime = ZonedDateTime.now(zoneId)
        return zonedDateTime.format(formatter)
    }

    fun buildWeatherDataResponse(): WeatherDataResponse {
        return WeatherDataResponse(
            Temperature(
                listOf(
                    Data(
                        "King's Park",
                        "C",
                        25
                    ),
                    Data(
                        "Hong Kong Observatory",
                        "C",
                        26
                    ),
                    Data(
                        "Wong Chuk Hang",
                        "C",
                        26
                    ),
                    Data(
                        "Ta Kwu Ling",
                        "C",
                        26
                    ),
                    Data(
                        "Lau Fau Shan",
                        "C",
                        25
                    ),
                    Data(
                        "Tai Po",
                        "C",
                        26
                    ),
                    Data(
                        "Sha Tin",
                        "C",
                        26
                    ),
                    Data(
                        "Tuen Mun",
                        "C",
                        27
                    ),
                    Data(
                        "Tseung Kwan 0",
                        "C",
                        25
                    ),
                    Data(
                        "Sai Kung",
                        "C",
                        26
                    ),
                    Data(
                        "Cheung Chau",
                        "C",
                        25
                    ),
                    Data(
                        "Chek Lap Kok",
                        "C",
                        27
                    ),
                    Data(
                        "Tsing Yi",
                        "C",
                        26
                    ),
                    Data(
                        "Shek Kong",
                        "C",
                        26
                    ),
                    Data(
                        "Tsuen Wan Shing Mun Valley",
                        "C",
                        25
                    ),
                    Data(
                        "Hong Kong Park",
                        "C",
                        25
                    ),
                    Data(
                        "Shau Kei Wan",
                        "C",
                        25
                    ),
                    Data(
                        "Kowloon City",
                        "C",
                        25
                    ),
                    Data(
                        "Happy Valley",
                        "C",
                        26
                    ),
                    Data(
                        "Wong Tai Sin",
                        "C",
                        26
                    ),
                    Data(
                        "Stanley",
                        "C",
                        25
                    ),
                    Data(
                        "Kwun Tong",
                        "C",
                        25
                    ),
                    Data(
                        "Sham Shui Po",
                        "C",
                        26
                    ),
                    Data(
                        "Kai Tak Runway Park",
                        "C",
                        26
                    ),
                    Data(
                        "Yuen Long Park",
                        "C",
                        26
                    ),
                    Data(
                        "Tai Mei Tuk",
                        "C",
                        25
                    )
                ),
                getTimeStamp()
            )
        )
    }
}