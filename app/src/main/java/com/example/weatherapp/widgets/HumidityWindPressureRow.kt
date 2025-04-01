package com.example.weatherapp.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HumidityWindPressureRow(
    humidity: Int,
    wind: Double,
    pressure: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        HumidityRow(humidity = humidity)
        PressureRow(pressure = pressure)
        WindRow(wind = wind)
    }
}

@Composable
fun HumidityRow(
    humidity: Int,
    modifier: Modifier = Modifier
) {
    MetricRow(
        icon = painterResource(id = com.example.weatherapp.R.drawable.droplets),
        value = "$humidity%",
        label = "Humidity",
        modifier = modifier
    )
}

@Composable
fun WindRow(
    wind: Double,
    modifier: Modifier = Modifier
) {
    MetricRow(
        icon = painterResource(id = com.example.weatherapp.R.drawable.wind),
        value = "$wind mph",
        label = "Wind",
        modifier = modifier
    )
}

@Composable
fun PressureRow(
    pressure: Int,
    modifier: Modifier = Modifier
) {
    MetricRow(
        icon = painterResource(id = com.example.weatherapp.R.drawable.pressure),
        value = "$pressure hPa",
        label = "Pressure",
        modifier = modifier
    )
}

@Composable
fun MetricRow(
    icon: Painter,
    value: String,
    label: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = icon,
            contentDescription = label,
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = value,
            style = TextStyle(
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        )

        Text(
            text = label,
            style = TextStyle(
                color = Color.White.copy(alpha = 0.7f),
                fontSize = 12.sp
            )
        )
    }
}