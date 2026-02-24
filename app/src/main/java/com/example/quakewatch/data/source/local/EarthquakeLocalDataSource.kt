package com.example.quakewatch.data.source.local

import androidx.datastore.core.DataStore
import com.example.quakewatch.data.source.local.datastore.PreferenceDataSource
import com.example.quakewatch.data.source.local.datastore.SortType
import com.example.quakewatch.data.source.local.datastore.UserPreference
import com.example.quakewatch.data.source.local.room.EarthquakeDao
import com.example.quakewatch.data.source.local.room.LocalEarthquake
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EarthquakeLocalDataSource @Inject constructor(
    val dao: EarthquakeDao,
    val preferenceDataSource: PreferenceDataSource
) : LocalDataSource {

    override suspend fun upsertAll(localEarthquakes: List<LocalEarthquake>) {
        dao.upsertAll(localEarthquakes)
    }

//    override fun observeAll(): Flow<List<LocalEarthquake>> {
//        return dao.observeAll()
//    }

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

    override fun getUserPreference(): Flow<UserPreference> {
        return preferenceDataSource.getUserPreference()
    }

    override suspend fun setSortType(sortType: SortType) {
        preferenceDataSource.setSortType(sortType)
    }

}