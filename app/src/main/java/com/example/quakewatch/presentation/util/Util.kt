package com.example.quakewatch.presentation.util

import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


fun formatMagnitude(magnitude: Double): String {
    val magnitudeFormat = DecimalFormat("0.0")
    return magnitudeFormat.format(magnitude)
}

fun getLocation(place: String?): String {
    return place?.let { place ->
        if (place.contains("of")) {
            val location: Array<String> =
                place.split("(?<=of )".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            return location[1]
        } else {
            return place
        }
    } ?: "Unknown Location"
}

fun getOffset(place: String?): String {
    return place?.let { place ->
        if (place.contains("of")) {
            val offset: Array<String> =
                place.split("(?<=of )".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            return offset[0]
        } else {
            return "NEAR THE"
        }
    } ?: "Unknown Location"
}

fun formatDate(time: Long): String {
    val dateObject = Date(time)
    val dateFormat = SimpleDateFormat("LLL dd, yyyy", Locale.getDefault())
    return dateFormat.format(dateObject)
}

fun formatTime(time: Long): String {
    val dateObject = Date(time)
    val dateFormat = SimpleDateFormat("h:mm a", Locale.getDefault())
    return dateFormat.format(dateObject)
}

fun formatDateTime(time: Long): String {
    val dateObject = Date(time)
    val dateFormat = SimpleDateFormat("LLL dd, yyyy h:mm a", Locale.getDefault())
    return dateFormat.format(dateObject)
}

fun formatThreeDecimal(number: Double): String? {
    val magnitudeFormat = DecimalFormat("0.000")
    return magnitudeFormat.format(number)
}

