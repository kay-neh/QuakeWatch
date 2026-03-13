package com.example.quakewatch.data.source.local

import com.example.quakewatch.data.source.local.datastore.AppPreference
import com.example.quakewatch.data.source.local.room.LocalEarthquake
import com.example.quakewatch.domain.model.SortType
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    suspend fun upsertAll(localEarthquakes: List<LocalEarthquake>)

    fun observeAllByTime(): Flow<List<LocalEarthquake>>

    fun observeAllByMagnitude(): Flow<List<LocalEarthquake>>

    fun observeById(eventId: String): Flow<LocalEarthquake>

    suspend fun getAll(): List<LocalEarthquake>

    suspend fun getById(eventId: String): LocalEarthquake?

    suspend fun deleteAll()

    fun getAppPreference(): Flow<AppPreference>

    suspend fun setSortType(sortType: SortType)

    suspend fun setDarkTheme(isDarkTheme: Boolean)


}