package com.example.quakewatch.presentation.screen.earthquakeDetail

import com.google.android.gms.maps.model.LatLng

data class EarthquakeDetailUIState(
    val earthquakeDetail: EarthquakeDetail = EarthquakeDetail(
        eventId = "",
        magnitude = "",
        place = "",
        time = "",
        url = "",
        latitude = 0.0,
        longitude = 0.0,
        depth = 0.0,
        markerLocation = null
    )
)

data class EarthquakeDetail(
    val eventId: String,
    val magnitude: String,
    val place: String,
    val time: String,
    val url: String,
    val latitude: Double,
    val longitude: Double,
    val depth: Double,
    val markerLocation: LatLng?
)