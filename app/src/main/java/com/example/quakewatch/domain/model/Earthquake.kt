package com.example.quakewatch.domain.model

import com.example.quakewatch.presentation.earthquakeDetail.EarthquakeDetail
import com.example.quakewatch.presentation.earthquakakeFeed.EarthquakeFeed
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class Earthquake(
    val eventId: String,
    val magnitude: Double,
    val place: String,
    val time: Long,
    val url: String,
    val longitude: Double,
    val latitude: Double,
    val depth: Double
)

fun Earthquake.toEarthquakeFeed(): EarthquakeFeed {
    return EarthquakeFeed(
        eventId = eventId,
        magnitude = formatMagnitude(magnitude),
        location = getLocation(place),
        offset = getOffset(place),
        date = formatDate(time),
        time = formatTime(time)
    )
}

fun List<Earthquake>.toEarthquakeFeedList(): List<EarthquakeFeed> {
    return this.map {
        it.toEarthquakeFeed()
    }
}

fun Earthquake.toEarthquakeDetail(): EarthquakeDetail {
    return EarthquakeDetail(
        eventId = eventId,
        magnitude = formatMagnitude(magnitude),
        place = getLocation(place),
        time = formatDateTime(time),
        url = url,
        longitude = 0.0,
        latitude = 0.0,
        depth = 0.0
    )
}

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

