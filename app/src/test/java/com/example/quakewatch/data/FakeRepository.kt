package com.example.quakewatch.data

import com.example.quakewatch.data.mapper.toExternal
import com.example.quakewatch.data.mapper.toLocal
import com.example.quakewatch.data.source.local.datastore.AppPreference
import com.example.quakewatch.data.source.local.room.LocalEarthquake
import com.example.quakewatch.data.source.network.NetworkEarthquake
import com.example.quakewatch.domain.model.Earthquake
import com.example.quakewatch.domain.model.SortType
import com.example.quakewatch.domain.model.UserPreference
import com.example.quakewatch.domain.repository.QuakeWatchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class FakeRepository : QuakeWatchRepository {

    private val networkEarthquakes = mutableListOf<NetworkEarthquake>()
    private val localEarthquakes = mutableListOf<LocalEarthquake>()
    private val appPreference = AppPreference()

    fun setNetworkResponse(networkEarthquakes: List<NetworkEarthquake>) {
        networkEarthquakes.forEach {
            this.networkEarthquakes.add(it)
        }
    }

    fun getLocalResponses(): Flow<List<LocalEarthquake>> {
        return flowOf(localEarthquakes)
    }

    override suspend fun loadEarthquakes(): List<NetworkEarthquake> {
        return networkEarthquakes
    }

    override suspend fun upsertEarthquakes(localEarthquakes: List<LocalEarthquake>) {
        this.localEarthquakes.addAll(localEarthquakes)
    }

    override fun getEarthquakesSortByTimeStream(): Flow<List<Earthquake>> {
        return flowOf(
            localEarthquakes
            .sortedByDescending {
                it.time
            }
            .map {
                it.toExternal()
            }
        )
    }

    override fun getEarthquakesSortByMagnitudeStream(): Flow<List<Earthquake>> {
        return flowOf(
            localEarthquakes
            .sortedByDescending {
                it.magnitude
            }
            .map {
                it.toExternal()
            }
        )
    }

    override fun getEarthquakeStreamById(eventId: String): Flow<Earthquake> {
        return flow {
            localEarthquakes.find { it.eventId == eventId }?.let { emit(it.toExternal()) }
        }
    }

    override suspend fun deleteEarthquakes() {
        localEarthquakes.clear()
    }

    override fun getUserPreference(): Flow<UserPreference> {
        return flowOf(appPreference).map {
            it.toExternal()
        }
    }

    override suspend fun setSortType(sortType: SortType) {
        appPreference.sortType = sortType
    }

    override suspend fun setDarkTheme(isDarkTheme: Boolean) {
        appPreference.isDarkTheme = isDarkTheme
    }
}