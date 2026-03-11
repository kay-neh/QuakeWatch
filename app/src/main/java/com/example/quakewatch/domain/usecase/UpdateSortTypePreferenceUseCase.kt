package com.example.quakewatch.domain.usecase

import com.example.quakewatch.data.source.local.datastore.SortType
import com.example.quakewatch.domain.repository.QuakeWatchRepository

class UpdateSortTypePreferenceUseCase(
    private val quakeWatchRepository: QuakeWatchRepository
) {

    suspend operator fun invoke(sortType: SortType) {
        quakeWatchRepository.setSortType(sortType)
    }

}