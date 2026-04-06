package com.example.quakewatch.domain.usecase

import android.util.Log
import com.example.quakewatch.data.mapper.toLocal
import com.example.quakewatch.domain.repository.QuakeWatchRepository
import com.example.quakewatch.domain.util.NetworkError
import com.example.quakewatch.domain.util.Result

class RefreshEarthquake(
    private val quakeWatchRepository: QuakeWatchRepository
) {
    suspend operator fun invoke(): Result<Unit, NetworkError> {
        return when (val networkResponse = quakeWatchRepository.loadEarthquakes()) {
            is Result.Error -> {
                Result.Error(networkResponse.error)
            }

            is Result.Success -> {
                quakeWatchRepository.upsertEarthquakes(networkResponse.data.toLocal())
                Result.Success(Unit)
            }
        }
    }
}