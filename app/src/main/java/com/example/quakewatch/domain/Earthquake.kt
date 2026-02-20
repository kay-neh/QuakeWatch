package com.example.quakewatch.domain

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