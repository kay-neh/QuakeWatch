package com.example.quakewatch.data.source.local.datastore

import androidx.datastore.core.DataStore
import com.example.quakewatch.domain.model.SortType
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class QuakeWatchPreferencesDataSource @Inject constructor(
    val dataStore: DataStore<AppPreference>
) : PreferenceDataSource {

    override fun getAppPreference(): Flow<AppPreference> {
        return dataStore.data
    }

    override suspend fun setSortType(sortType: SortType) {
        dataStore.updateData {
            it.copy(sortType = sortType)
        }
    }

    override suspend fun setDarkTheme(isDarkTheme: Boolean) {
        dataStore.updateData {
            it.copy(isDarkTheme = isDarkTheme)
        }
    }

}