package com.example.quakewatch.domain.usecase

import com.example.quakewatch.domain.model.UserPreference
import com.example.quakewatch.domain.repository.QuakeWatchRepository
import kotlinx.coroutines.flow.Flow

class GetUserPreference(
    private val quakeWatchRepository: QuakeWatchRepository
) {
    operator fun invoke(): Flow<UserPreference> {
        return quakeWatchRepository.getUserPreference()
    }
}