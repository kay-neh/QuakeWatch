package com.example.quakewatch.presentation.util

import android.content.Context
import androidx.core.content.ContextCompat
import com.example.quakewatch.R
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.floor


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

fun getMagnitudeColor(context: Context, magnitude: Double): Int {
    val magnitudeColorResourceId: Int
    val magnitudeFloor = floor(magnitude).toInt()
    magnitudeColorResourceId = when (magnitudeFloor) {
        0, 1 -> R.color.magnitude1
        2 -> R.color.magnitude2
        3 -> R.color.magnitude3
        4 -> R.color.magnitude4
        5 -> R.color.magnitude5
        6 -> R.color.magnitude6
        7 -> R.color.magnitude7
        8 -> R.color.magnitude8
        9 -> R.color.magnitude9
        else -> R.color.magnitude10plus
    }
    return ContextCompat.getColor(context, magnitudeColorResourceId)
}