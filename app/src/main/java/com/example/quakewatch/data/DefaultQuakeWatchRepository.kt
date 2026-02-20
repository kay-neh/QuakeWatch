package com.example.quakewatch.data

import com.example.quakewatch.data.source.local.LocalDataSource
import com.example.quakewatch.data.source.local.LocalEarthquake
import com.example.quakewatch.data.source.network.NetworkDataSource
import com.example.quakewatch.data.source.network.NetworkEarthquakeProperty
import com.example.quakewatch.domain.QuakeWatchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultQuakeWatchRepository @Inject constructor(
    val networkDataSource: NetworkDataSource,
    val localDataSource: LocalDataSource
): QuakeWatchRepository {
    override suspend fun upsertEarthquakes() {
        val response = networkDataSource.loadEarthquakes()
        if(response.isSuccessful) {
            response.body()?.networkEarthquakes?.let {
                localDataSource.upsertAll(it.toLocal())
            }
        }
    }

    override fun getEarthquakesStream(): Flow<List<LocalEarthquake>> {
        TODO("Not yet implemented")
    }

    override fun getEarthquakeStreamById(eventId: String): Flow<LocalEarthquake> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteEarthquakes() {
        localDataSource.deleteAll()
    }


}