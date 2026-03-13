package com.example.quakewatch.data.source.local.datastore

import com.example.quakewatch.domain.model.SortType
import kotlinx.coroutines.flow.Flow

interface PreferenceDataSource {

    fun getAppPreference(): Flow<AppPreference>

    suspend fun setSortType(sortType: SortType)

    suspend fun setDarkTheme(isDarkTheme: Boolean)


}
