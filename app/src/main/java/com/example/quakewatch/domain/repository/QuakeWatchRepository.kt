package com.example.quakewatch.domain.repository

import com.example.quakewatch.data.source.local.room.LocalEarthquake
import com.example.quakewatch.data.source.network.NetworkEarthquake
import com.example.quakewatch.domain.model.Earthquake
import com.example.quakewatch.domain.model.SortType
import com.example.quakewatch.domain.model.UserPreference
import com.example.quakewatch.domain.util.NetworkError
import com.example.quakewatch.domain.util.Result
import kotlinx.coroutines.flow.Flow

interface QuakeWatchRepository {

    suspend fun loadEarthquakes(): Result<List<NetworkEarthquake>, NetworkError>

    suspend fun upsertEarthquakes(localEarthquakes: List<LocalEarthquake>)

    fun getEarthquakesSortByTimeStream(): Flow<List<Earthquake>>

    fun getEarthquakesSortByMagnitudeStream(): Flow<List<Earthquake>>

    fun getEarthquakeStreamById(eventId: String): Flow<Earthquake>

    suspend fun deleteEarthquakes()

    fun getUserPreference(): Flow<UserPreference>

    suspend fun setSortType(sortType: SortType)

    suspend fun setDarkTheme(isDarkTheme: Boolean)

}