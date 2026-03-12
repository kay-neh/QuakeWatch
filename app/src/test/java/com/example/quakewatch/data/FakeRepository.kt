package com.example.quakewatch.data

import com.example.quakewatch.data.source.local.datastore.SortType
import com.example.quakewatch.data.source.local.datastore.UserPreference
import com.example.quakewatch.data.source.local.room.LocalEarthquake
import com.example.quakewatch.data.source.network.NetworkEarthquake
import com.example.quakewatch.domain.model.Earthquake
import com.example.quakewatch.domain.repository.QuakeWatchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class FakeRepository : QuakeWatchRepository {

    private val networkEarthquakes = mutableListOf<NetworkEarthquake>()
    private val localEarthquakes = mutableListOf<LocalEarthquake>()
    private val userPreference = UserPreference()

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
        return flowOf(userPreference)
    }

    override suspend fun setSortType(sortType: SortType) {
        userPreference.sortType = sortType
    }
}