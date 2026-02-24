package com.example.quakewatch.domain.usecase

import com.example.quakewatch.data.source.local.datastore.SortType
import com.example.quakewatch.data.source.local.datastore.UserPreference
import com.example.quakewatch.domain.repository.QuakeWatchRepository

class UpdateUserPreferenceUseCase(
    private val quakeWatchRepository: QuakeWatchRepository
) {

    suspend operator fun invoke(userPreference: UserPreference) {
        //quakeWatchRepository.updateUserPreference(userPreference)
    }

    private suspend fun updateSortType(sortType: SortType) {
        quakeWatchRepository.setSortType(sortType)
    }

}