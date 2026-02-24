package com.example.quakewatch.domain.usecase

import com.example.quakewatch.data.source.local.datastore.UserPreference
import com.example.quakewatch.domain.repository.QuakeWatchRepository
import kotlinx.coroutines.flow.Flow

class GetUserPreferenceUseCase(
    private val quakeWatchRepository: QuakeWatchRepository
) {
    operator fun invoke(): Flow<UserPreference> {
        return quakeWatchRepository.getUserPreference()
    }
}