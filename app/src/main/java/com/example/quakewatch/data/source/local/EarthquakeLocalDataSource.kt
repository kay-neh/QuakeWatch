package com.example.quakewatch.data.source.local

import com.example.quakewatch.data.source.local.datastore.PreferenceDataSource
import com.example.quakewatch.data.source.local.datastore.AppPreference
import com.example.quakewatch.data.source.local.room.EarthquakeDao
import com.example.quakewatch.data.source.local.room.LocalEarthquake
import com.example.quakewatch.domain.model.SortType
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EarthquakeLocalDataSource @Inject constructor(
    val dao: EarthquakeDao,
    val preferenceDataSource: PreferenceDataSource
) : LocalDataSource {

    override suspend fun upsertAll(localEarthquakes: List<LocalEarthquake>) {
        dao.upsertAll(localEarthquakes)
    }

    override fun observeAllByTime(): Flow<List<LocalEarthquake>> {
        return dao.observeAllByTime()
    }

    override fun observeAllByMagnitude(): Flow<List<LocalEarthquake>> {
        return dao.observeAllByMagnitude()
    }

    override fun observeById(eventId: String): Flow<LocalEarthquake> {
        return dao.observeById(eventId)
    }

    override suspend fun getAll(): List<LocalEarthquake> {
        return dao.getAll()
    }

    override suspend fun getById(eventId: String): LocalEarthquake? {
        return dao.getById(eventId)
    }

    override suspend fun deleteAll() {
        dao.deleteAll()
    }

    override fun getAppPreference(): Flow<AppPreference> {
        return preferenceDataSource.getAppPreference()
    }

    override suspend fun setSortType(sortType: SortType) {
        preferenceDataSource.setSortType(sortType)
    }

    override suspend fun setDarkTheme(isDarkTheme: Boolean) {
        preferenceDataSource.setDarkTheme(isDarkTheme)
    }

}