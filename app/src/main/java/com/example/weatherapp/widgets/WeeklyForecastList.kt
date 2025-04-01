package com.example.weatherapp.widgets

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.model.Weather
import com.example.weatherapp.model.WeatherObject
import com.example.weatherapp.utils.formatDate
import com.example.weatherapp.utils.formatDateTime

@Composable
fun WeeklyForecastList(
    data: Weather,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
    ) {
        // Group forecasts by day
        val groupedForecasts = data.list.groupBy { forecast ->
            // Assuming you have a method to get the date in a proper format
            formatDate(forecast.dt)
        }

        // Show one item per day
        groupedForecasts.forEach { (date, forecasts) ->
            val mainForecast = forecasts.first() // Take the first forecast of the day

            item {
                WeeklyForecastRow(
                    date = date,
                    weatherObject = mainForecast
                )

                if (date != groupedForecasts.keys.last()) {
                    Divider(
                        color = Color.White.copy(alpha = 0.2f),
                        thickness = 0.5.dp,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            }
        }
    }
}

// Non-scrolling version for use in the main scrollable content
@Composable
fun ForecastContent(
    data: Weather,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        // Group forecasts by day
        val groupedForecasts = data.list.groupBy { forecast ->
            formatDate(forecast.dt)
        }

        // Show one item per day
        groupedForecasts.forEach { (date, forecasts) ->
            val mainForecast = forecasts.first()

            WeeklyForecastRow(
                date = date,
                weatherObject = mainForecast
            )

            if (date != groupedForecasts.keys.last()) {
                Divider(
                    color = Color.White.copy(alpha = 0.2f),
                    thickness = 0.5.dp,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }
        }
    }
}

@Composable
fun WeeklyForecastRow(
    date: String,
    weatherObject: WeatherObject,
    modifier: Modifier = Modifier
) {
    val imageUrl = "https://openweathermap.org/img/wn/${weatherObject.weather[0].icon}.png"

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Day
        Text(
            text = date,
            style = TextStyle(
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            ),
            modifier = Modifier.weight(1f)
        )

        // Weather icon
        WeatherStateImage(
            imageUrl = imageUrl,
            modifier = Modifier.size(40.dp)
        )

        // Weather description
        Text(
            text = weatherObject.weather[0].main,
            style = TextStyle(
                color = Color.White.copy(alpha = 0.8f),
                fontSize = 14.sp
            ),
            modifier = Modifier
                .width(80.dp)
                .padding(horizontal = 8.dp)
        )

        // Temperature
        Text(
            text = "${weatherObject.main.temp.toInt()}°C",
            style = TextStyle(
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.width(50.dp)
        )
    }
}