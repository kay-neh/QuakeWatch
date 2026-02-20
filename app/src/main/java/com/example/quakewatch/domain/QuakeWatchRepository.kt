package com.example.quakewatch.domain

import com.example.quakewatch.data.source.local.LocalEarthquake
import kotlinx.coroutines.flow.Flow

interface QuakeWatchRepository {

    suspend fun upsertEarthquakes()

    fun getEarthquakesStream(): Flow<List<LocalEarthquake>>

    fun getEarthquakeStreamById(eventId: String): Flow<LocalEarthquake>

    suspend fun deleteEarthquakes()



}