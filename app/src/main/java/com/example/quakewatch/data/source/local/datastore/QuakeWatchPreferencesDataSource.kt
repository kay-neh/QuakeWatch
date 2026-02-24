package com.example.quakewatch.data.source.local.datastore

import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class QuakeWatchPreferencesDataSource @Inject constructor(
    val dataStore: DataStore<UserPreference>
) : PreferenceDataSource {

    override fun getUserPreference(): Flow<UserPreference> {
        return dataStore.data
    }

    override suspend fun setSortType(sortType: SortType) {
        dataStore.updateData {
            it.copy(sortType = sortType)
        }
    }

}