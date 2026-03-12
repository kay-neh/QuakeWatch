package com.example.quakewatch.domain.usecase

import com.example.quakewatch.data.source.local.datastore.SortType
import com.example.quakewatch.domain.model.Earthquake
import com.example.quakewatch.domain.repository.QuakeWatchRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest

class GetSortedEarthquakes(
    private val quakeWatchRepository: QuakeWatchRepository
) {

    private val userPreference = quakeWatchRepository.getUserPreference()

    @OptIn(ExperimentalCoroutinesApi::class)
    operator fun invoke(): Flow<List<Earthquake>> {
        return userPreference.flatMapLatest { userPreference ->
            when (userPreference.sortType) {
                SortType.BY_TIME -> sortEarthquakes(SortType.BY_TIME)
                SortType.BY_MAGNITUDE -> sortEarthquakes(SortType.BY_MAGNITUDE)
            }
        }
    }

    private fun sortEarthquakes(sortType: SortType): Flow<List<Earthquake>> {
        return when (sortType) {
            SortType.BY_TIME -> quakeWatchRepository.getEarthquakesSortByTimeStream()
            SortType.BY_MAGNITUDE -> quakeWatchRepository.getEarthquakesSortByMagnitudeStream()
        }
    }

}