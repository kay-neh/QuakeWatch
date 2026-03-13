package com.example.quakewatch.domain.usecase

import com.example.quakewatch.domain.model.SortType
import com.example.quakewatch.domain.repository.QuakeWatchRepository

class UpdateSortTypePreference(
    private val quakeWatchRepository: QuakeWatchRepository
) {

    suspend operator fun invoke(sortType: SortType) {
        quakeWatchRepository.setSortType(sortType)
    }

}