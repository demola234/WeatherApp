package com.example.weatherapp.network


import androidx.core.text.util.LocalePreferences
import com.example.weatherapp.model.WeatherObject
import com.example.weatherapp.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton


@Singleton
interface WeatherApi {
    @GET(value = "data/2.5/forecast/daily")
    suspend fun getWeather(
        @Query("q") query: String,
        @Query("unit") units: String = "imperial",
        @Query("appid") appid: String = Constants.API_KEY,
        
        ): WeatherObject
}