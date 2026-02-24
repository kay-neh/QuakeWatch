package com.example.quakewatch.data.source.local.datastore

import kotlinx.coroutines.flow.Flow

interface PreferenceDataSource {

    fun getUserPreference(): Flow<UserPreference>

    suspend fun setSortType(sortType: SortType)

}
