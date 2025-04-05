package com.example.weatherapp.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.weatherapp.model.Weather
import com.example.weatherapp.navigation.WeatherScreens
import com.example.weatherapp.utils.currentDate
import com.example.weatherapp.utils.formatDateTime
import com.example.weatherapp.viewModel.WeatherViewModel
import com.example.weatherapp.widgets.ForecastContent
import com.example.weatherapp.widgets.HumidityWindPressureRow
import com.example.weatherapp.widgets.SunsetSunRiseRow
import com.example.weatherapp.widgets.WeatherStateImage
import com.example.weatherapp.widgets.WeatherTopBar


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController, weatherViewModel: WeatherViewModel) {
    val gradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF1E88E5),
            Color(0xFF0D47A1)
        )
    )

    if (weatherViewModel.data.value.loading == true) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(gradient),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                color = Color.White,
                modifier = Modifier.size(50.dp)
            )
        }
    } else {
        Scaffold(
            topBar = {
                WeatherTopBar(
                    title = "${weatherViewModel.data.value.data?.city?.name.toString()}, ${weatherViewModel.data.value.data?.city?.country.toString()}",
                    navController = navController,
                    onAddActionClicked = {
                    navController.navigate(WeatherScreens.SearchScreen.name)
                    }
                )
            },
            content = { paddingValues ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(gradient)
                        .padding(paddingValues)
                ) {
                    MainContent(data = weatherViewModel.data.value.data)
                }
            }
        )
    }
}

@Composable
fun MainContent(data: Weather?) {
    val imageUrl =
        "https://openweathermap.org/img/wn/${data?.list?.get(0)?.weather?.get(0)?.icon}@2x.png"

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Date display
            Text(
                text = currentDate(),
                style = TextStyle(
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                ),
                modifier = Modifier.padding(bottom = 24.dp)
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0x33FFFFFF)
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 0.dp
                )
            ) {
                Column(
                    modifier = Modifier
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Weather icon
                    WeatherStateImage(
                        imageUrl = imageUrl,
                        modifier = Modifier.size(100.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Temperature
                    Text(
                        text = "${data?.list?.get(0)?.main?.temp?.toInt()}°C",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 60.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )

                    // Weather description
                    Text(
                        text = data?.list?.get(0)?.weather?.get(0)?.main.toString(),
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Medium
                        ),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    // Weather details
                    Text(
                        text = data?.list?.get(0)?.weather?.get(0)?.description?.capitalize() ?: "",
                        style = TextStyle(
                            color = Color.White.copy(alpha = 0.8f),
                            fontSize = 16.sp
                        )
                    )
                }
            }

            // Weather metrics cards
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0x33FFFFFF)
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 0.dp
                )
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    // Humidity, Wind, Pressure
                    HumidityWindPressureRow(
                        humidity = data?.list?.get(0)?.main?.humidity ?: 0,
                        wind = data?.list?.get(0)?.wind?.speed ?: 0.0,
                        pressure = data?.list?.get(0)?.main?.pressure ?: 0
                    )

                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 12.dp),
                        thickness = 1.dp,
                        color = Color.White.copy(alpha = 0.3f)
                    )

                    // Sunrise, Sunset
                    SunsetSunRiseRow(
                        sunrise = formatDateTime(timeStamp = data?.city?.sunrise!!),
                        sunset = formatDateTime(timeStamp = data.city.sunset)
                    )
                }
            }

            // Forecast card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0x33FFFFFF)
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 0.dp
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "5-Day Forecast",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    if (data != null) {
                        ForecastContent(data = data)
                    }
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

// String extension function
fun String.capitalize(): String {
    return this.replaceFirstChar {
        if (it.isLowerCase()) it.titlecase() else it.toString()
    }
}
