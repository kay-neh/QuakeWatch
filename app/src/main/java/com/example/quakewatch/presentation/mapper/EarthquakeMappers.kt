package com.example.quakewatch.presentation.mapper

import com.example.quakewatch.domain.model.Earthquake

import com.example.quakewatch.presentation.screen.earthquakeFeed.EarthquakeFeed
import com.example.quakewatch.presentation.screen.earthquakeDetail.EarthquakeDetail
import com.example.quakewatch.presentation.util.formatDate
import com.example.quakewatch.presentation.util.formatDateTime
import com.example.quakewatch.presentation.util.formatMagnitude
import com.example.quakewatch.presentation.util.formatTwoDecimal
import com.example.quakewatch.presentation.util.formatTime
import com.example.quakewatch.presentation.util.getLocation
import com.example.quakewatch.presentation.util.getOffset
import com.google.android.gms.maps.model.LatLng

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
        latitude = latitude,
        longitude = longitude,
        depth = formatTwoDecimal(depth).toDouble(),
        markerLocation = LatLng(latitude, longitude)
    )
}
