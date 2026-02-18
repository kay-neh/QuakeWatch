package com.example.quakewatch.data.local

import com.example.quakewatch.data.local.model.Earthquake
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class QuakeWatchLocalDataSource @Inject constructor(
    val database: QuakeWatchDatabase,
) {

    suspend fun upsertEarthquakes(earthquakes: List<Earthquake>) {
        database.dao.upsertEarthquakes(earthquakes)
    }

    suspend fun getEarthquakes(): Flow<List<Earthquake>> {
        return database.dao.getEarthquakes()
    }

    suspend fun getEarthquakeById(eventId: String): Earthquake? {
        return database.dao.getEarthquakeById(eventId)
    }

    suspend fun deleteEarthquakes() {
        database.dao.deleteEarthquakes()
    }

}