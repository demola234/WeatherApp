package com.example.weatherapp.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R

@Composable
fun SunsetSunRiseRow(
    sunrise: String,
    sunset: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        SunRiseRow(sunrise = sunrise)
        SunsetRow(sunset = sunset)
    }
}

@Composable
fun SunRiseRow(
    sunrise: String,
    modifier: Modifier = Modifier
) {
    SunTimeRow(
        icon = painterResource(id = R.drawable.sunrise),
        time = sunrise,
        label = "Sunrise",
        modifier = modifier
    )
}

@Composable
fun SunsetRow(
    sunset: String,
    modifier: Modifier = Modifier
) {
    SunTimeRow(
        icon = painterResource(id = R.drawable.sunset),
        time = sunset,
        label = "Sunset",
        modifier = modifier
    )
}


@Composable
fun SunTimeRow(
    icon: Painter,
    time: String,
    label: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = icon,
            contentDescription = label,
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )


        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Text(
                text = time,
                style = TextStyle(
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
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
}