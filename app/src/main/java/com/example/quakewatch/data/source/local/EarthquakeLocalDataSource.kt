package com.example.quakewatch.data.source.local

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EarthquakeLocalDataSource @Inject constructor(
    val dao: EarthquakeDao,
) : LocalDataSource {

    override suspend fun upsertAll(localEarthquakes: List<LocalEarthquake>) {
        dao.upsertAll(localEarthquakes)
    }

    override fun observeAll(): Flow<List<LocalEarthquake>> {
        return dao.observeAll()
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

}