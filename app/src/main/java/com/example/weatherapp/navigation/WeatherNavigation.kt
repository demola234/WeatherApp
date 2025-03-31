package com.example.weatherapp.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.WeatherApp
import com.example.weatherapp.screen.SplashScreen
import com.example.weatherapp.screen.MainScreen
import com.example.weatherapp.viewModel.WeatherViewModel

@Composable
fun WeatherNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = WeatherScreens.SplashScreen.name
    ) {
        composable(WeatherScreens.SplashScreen.name) {
            SplashScreen(navController = navController)
        }
        composable(WeatherScreens.MainScreen.name) {
            val weatherViewModel = hiltViewModel<WeatherViewModel>()
            MainScreen(navController = navController, weatherViewModel = weatherViewModel)
        }
    }
}