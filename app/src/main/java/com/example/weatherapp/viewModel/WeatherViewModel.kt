package com.example.weatherapp.viewModel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.DataException
import com.example.weatherapp.model.Weather
import com.example.weatherapp.model.WeatherObject
import com.example.weatherapp.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val repository: WeatherRepository) : ViewModel() {
   val data: MutableState<DataException<Weather, Boolean, Exception>> = mutableStateOf(
       DataException(data = null, loading =  true,e= Exception("")))

    init {
        loadWeather()
    }

    private fun loadWeather() {
        getWeather("Abuja")
    }

    private fun getWeather(cityQuery: String) {
       viewModelScope.launch {
           try {
               if (cityQuery.isEmpty()) {
                   data.value = DataException(data = null, loading = true, e = Exception(""))
               } else {
                 val  result = repository.getWeather(cityQuery)

                   Log.d("TAG", "getWeather: ${result.data}")

                   data.value = DataException(data = result.data, loading = false, e = Exception(""))
               }
           } catch (e: Exception) {
               data.value = DataException(data = null, loading = false, e = e)
           }
       }
    }
}