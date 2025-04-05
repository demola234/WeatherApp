package com.example.weatherapp.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.weatherapp.viewModel.WeatherViewModel
import com.example.weatherapp.widgets.SearchBar
import com.example.weatherapp.widgets.WeatherTopBar

@Composable
fun SearchScreen(
    navController: NavController,
    weatherViewModel: WeatherViewModel,
) {

    val gradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF1E88E5),
            Color(0xFF0D47A1)
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient)
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = Color.Transparent,
            topBar = {
                WeatherTopBar(
                    title = "Search",
                    icon = Icons.Outlined.ArrowBack,
                    isMainScreen = false,
                    elevation = 0.dp,
                    navController = navController,
                )
            }
        ) { paddingValues ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                color = Color.Transparent
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    content = {
                        SearchBar(
                            // Your SearchBar parameters
                        )
                    },
                )
            }
        }

}
}