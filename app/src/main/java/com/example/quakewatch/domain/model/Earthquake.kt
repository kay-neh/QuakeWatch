package com.example.quakewatch.domain.model

data class Earthquake(
    val eventId: String,
    val magnitude: Double,
    val place: String,
    val time: Long,
    val url: String,
    val latitude: Double,
    val longitude: Double,
    val depth: Double
)