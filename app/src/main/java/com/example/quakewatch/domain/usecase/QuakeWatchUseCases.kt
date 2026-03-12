package com.example.quakewatch.domain.usecase

data class QuakeWatchUseCases(
    val refreshEarthquake: RefreshEarthquake,
    val getSortedEarthquakes: GetSortedEarthquakes,
    val getEarthquake: GetEarthquake
)
