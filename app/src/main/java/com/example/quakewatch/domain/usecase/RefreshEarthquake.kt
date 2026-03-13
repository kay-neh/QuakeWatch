package com.example.quakewatch.domain.usecase

import com.example.quakewatch.data.mapper.toLocal
import com.example.quakewatch.domain.repository.QuakeWatchRepository

class RefreshEarthquake(
    private val quakeWatchRepository: QuakeWatchRepository
) {
    suspend operator fun invoke() {
        quakeWatchRepository.upsertEarthquakes(quakeWatchRepository.loadEarthquakes().toLocal())
    }
}