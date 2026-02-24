package com.example.quakewatch.data.source.local

import com.example.quakewatch.data.source.local.datastore.SortType
import com.example.quakewatch.data.source.local.datastore.UserPreference
import com.example.quakewatch.data.source.local.room.LocalEarthquake
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    suspend fun upsertAll(localEarthquakes: List<LocalEarthquake>)

    // fun observeAll(): Flow<List<LocalEarthquake>>

    fun observeAllByTime(): Flow<List<LocalEarthquake>>

    fun observeAllByMagnitude(): Flow<List<LocalEarthquake>>

    fun observeById(eventId: String): Flow<LocalEarthquake>

    suspend fun getAll(): List<LocalEarthquake>

    suspend fun getById(eventId: String): LocalEarthquake?

    suspend fun deleteAll()

    fun getUserPreference(): Flow<UserPreference>

    suspend fun setSortType(sortType: SortType)




}