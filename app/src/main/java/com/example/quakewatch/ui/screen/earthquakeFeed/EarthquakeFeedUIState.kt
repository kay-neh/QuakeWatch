package com.example.quakewatch.ui.screen.earthquakeFeed

data class EarthquakeFeedUIState(
    val earthquakes: List<EarthquakeFeed> = emptyList(),
)

data class EarthquakeFeed(
    val eventId: String,
    val magnitude: String,
    val location: String,
    val offset: String,
    val date: String,
    val time: String
)
