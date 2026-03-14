package com.example.quakewatch.domain.usecase

import android.util.Log
import com.example.quakewatch.data.mapper.toLocal
import com.example.quakewatch.domain.repository.QuakeWatchRepository

class RefreshEarthquake(
    private val quakeWatchRepository: QuakeWatchRepository
) {
    suspend operator fun invoke() {
        val localEarthquake = quakeWatchRepository.loadEarthquakes().toLocal()
        quakeWatchRepository.upsertEarthquakes(localEarthquake)
    }
}