package com.example.weatherapp.utils

import android.annotation.SuppressLint

@SuppressLint("SimpleDateFormat")
fun currentDate(): String {
    val date = java.util.Date()
    val dateFormat = java.text.SimpleDateFormat("dd MMMM yyyy")
    return dateFormat.format(date)
}

fun formatDate(timeStamp: Int): String {
    val dateFormat = java.text.SimpleDateFormat("EEE, MMM, dd")
    val newDate = java.util.Date(timeStamp.toLong() * 1000)

    return dateFormat.format(newDate)
}

fun formatDateTime(timeStamp: Int): String {
    val dateFormat = java.text.SimpleDateFormat("hh:mm a")
    val newDate = java.util.Date(timeStamp.toLong() * 1000)
    return dateFormat.format(newDate)
}

fun formatDecimal(number: Double): String {
    return String.format("%.2f", number)
}

