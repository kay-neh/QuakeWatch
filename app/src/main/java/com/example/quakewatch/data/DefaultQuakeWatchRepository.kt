package com.example.quakewatch.data

import com.example.quakewatch.data.mapper.toExternal
import com.example.quakewatch.data.source.local.LocalDataSource
import com.example.quakewatch.data.source.local.room.LocalEarthquake
import com.example.quakewatch.data.source.network.NetworkDataSource
import com.example.quakewatch.data.source.network.NetworkEarthquake
import com.example.quakewatch.domain.model.Earthquake
import com.example.quakewatch.domain.model.SortType
import com.example.quakewatch.domain.model.UserPreference
import com.example.quakewatch.domain.repository.QuakeWatchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DefaultQuakeWatchRepository @Inject constructor(
    val networkDataSource: NetworkDataSource,
    val localDataSource: LocalDataSource
): QuakeWatchRepository {

    override suspend fun loadEarthquakes(): List<NetworkEarthquake> {
        return networkDataSource.loadEarthquakes()
    }

    override suspend fun upsertEarthquakes(localEarthquakes: List<LocalEarthquake>) {
        localDataSource.upsertAll(localEarthquakes)
    }

    override fun getEarthquakesSortByTimeStream(): Flow<List<Earthquake>> {
        return localDataSource.observeAllByTime().map {
            it.toExternal()
        }
    }

    override fun getEarthquakesSortByMagnitudeStream(): Flow<List<Earthquake>> {
        return localDataSource.observeAllByMagnitude().map {
            it.toExternal()
        }
    }

    override fun getEarthquakeStreamById(eventId: String): Flow<Earthquake> {
        return localDataSource.observeById(eventId).map {
            it.toExternal()
        }
    }

    override suspend fun deleteEarthquakes() {
        localDataSource.deleteAll()
    }

    override fun getUserPreference(): Flow<UserPreference> {
        return localDataSource.getAppPreference().map {
            it.toExternal()
        }
    }

    override suspend fun setSortType(sortType: SortType) {
        localDataSource.setSortType(sortType)
    }

    override suspend fun setDarkTheme(isDarkTheme: Boolean) {
        localDataSource.setDarkTheme(isDarkTheme)
    }


}