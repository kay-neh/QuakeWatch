package com.example.quakewatch.domain.usecase

import com.example.quakewatch.domain.repository.QuakeWatchRepository

class SyncEarthquakeUseCase(
    private val quakeWatchRepository: QuakeWatchRepository
) {


}