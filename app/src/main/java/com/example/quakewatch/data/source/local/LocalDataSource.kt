package com.example.quakewatch.data.source.local

import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    suspend fun upsertAll(localEarthquakes: List<LocalEarthquake>)

    fun observeAll(): Flow<List<LocalEarthquake>>

    fun observeById(eventId: String): Flow<LocalEarthquake>

    suspend fun getAll(): List<LocalEarthquake>

    suspend fun getById(eventId: String): LocalEarthquake?

    suspend fun deleteAll()

}