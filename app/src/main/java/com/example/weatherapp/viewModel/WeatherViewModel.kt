package com.example.weatherapp.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.DataException
import com.example.weatherapp.model.WeatherObject
import com.example.weatherapp.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val repository: WeatherRepository) : ViewModel() {
   val data: MutableState<DataException<WeatherObject, Boolean, Exception>> = mutableStateOf(
       DataException(data = null, loading =  true,e= Exception("")))

    init {
        loadWeather()
    }

    private fun loadWeather() {
        getWeather("Abuja")
    }

    private fun getWeather(cityQuery: String) {
       viewModelScope.launch {
           if (cityQuery.isEmpty()) {
               data.value = DataException(data = null, loading = true, e = Exception(""))
           } else {
               data.value = repository.getWeather(cityQuery)
               data.value = DataException(data = null, loading = true, e = Exception(""))
           }
       }
    }
}