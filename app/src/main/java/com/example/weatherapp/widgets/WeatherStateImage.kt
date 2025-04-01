package com.example.weatherapp.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun WeatherStateImage(
    imageUrl: String,
    modifier: Modifier = Modifier,
) {
    GlideImage(
        model = imageUrl,
        contentDescription = "Weather Icon",
        modifier = modifier,
        contentScale = ContentScale.Fit,
        loading = placeholder {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = Color.White)
            }
        },
        failure = placeholder {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                androidx.compose.material3.Icon(
                    painter = androidx.compose.ui.res.painterResource(
                        id = com.example.weatherapp.R.drawable.weather
                    ),
                    contentDescription = "Weather Icon",
                    tint = Color.White
                )
            }
        }
    )
}
