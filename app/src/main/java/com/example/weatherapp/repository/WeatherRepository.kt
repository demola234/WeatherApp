package com.example.weatherapp.repository

import android.util.Log
import com.example.weatherapp.data.DataException
import com.example.weatherapp.model.Weather
import com.example.weatherapp.network.WeatherApi
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api: WeatherApi) {
    suspend fun getWeather(cityQuery: String): DataException<Weather, Boolean, Exception>  {
       val response = try {

           api.getWeather(cityQuery)
       } catch (e: Exception) {
           return DataException(
               data = null,
               loading = false,
               e = e
           )
       }

        Log.d("TAG", "getWeather: $response")
        return DataException(
            data = response,
            loading = false,
            e = null
        )
    }

}