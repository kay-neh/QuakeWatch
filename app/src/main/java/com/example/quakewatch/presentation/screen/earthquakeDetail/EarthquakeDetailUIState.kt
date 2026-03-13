package com.example.quakewatch.presentation.screen.earthquakeDetail

data class EarthquakeDetailUIState(
    val earthquakeDetail: EarthquakeDetail = EarthquakeDetail(
        eventId = "",
        magnitude = "",
        place = "",
        time = "",
        url = "",
        longitude = 0.0,
        latitude = 0.0,
        depth = 0.0
    )
)

data class EarthquakeDetail(
    val eventId: String,
    val magnitude: String,
    val place: String,
    val time: String,
    val url: String,
    val longitude: Double,
    val latitude: Double,
    val depth: Double
)