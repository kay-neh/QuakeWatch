package com.example.quakewatch.domain.usecase

data class QuakeWatchUseCases(
    val refreshEarthquake: RefreshEarthquakeUseCase,
    val getSortedEarthquakes: GetSortedEarthquakesUseCase,
    val getEarthquake: GetEarthquakeUseCase
)
