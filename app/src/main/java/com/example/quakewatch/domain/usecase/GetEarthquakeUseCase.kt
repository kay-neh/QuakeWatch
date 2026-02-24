package com.example.quakewatch.domain.usecase

import com.example.quakewatch.domain.model.Earthquake
import com.example.quakewatch.domain.repository.QuakeWatchRepository
import kotlinx.coroutines.flow.Flow

class GetEarthquakeUseCase(
    private val quakeWatchRepository: QuakeWatchRepository
) {
    operator fun invoke(eventId: String): Flow<Earthquake> {
        return quakeWatchRepository.getEarthquakeStreamById(eventId)
    }
}