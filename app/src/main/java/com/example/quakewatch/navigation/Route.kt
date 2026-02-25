package com.example.quakewatch.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface Route : NavKey {

    @Serializable
    data object EarthquakeList : Route

    @Serializable
    data class EarthquakeDetail(val eventId: String) : Route

    @Serializable
    data object Settings : Route
}