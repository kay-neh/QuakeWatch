package com.example.quakewatch.data.source.local.room

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.quakewatch.data.source.local.room.LocalEarthquake
import kotlinx.coroutines.flow.Flow

@Dao
interface EarthquakeDao {

    @Upsert
    suspend fun upsertAll(localEarthquakes: List<LocalEarthquake>)

    @Query("SELECT * FROM earthquake ORDER BY time DESC")
    fun observeAllByTime(): Flow<List<LocalEarthquake>>

    @Query("SELECT * FROM earthquake ORDER BY magnitude DESC")
    fun observeAllByMagnitude(): Flow<List<LocalEarthquake>>

    @Query("SELECT * FROM earthquake WHERE eventId = :eventId")
    fun observeById(eventId: String): Flow<LocalEarthquake>

    @Query("SELECT * FROM earthquake")
    suspend fun getAll(): List<LocalEarthquake>

    @Query("SELECT * FROM earthquake WHERE eventId = :eventId")
    suspend fun getById(eventId: String): LocalEarthquake?

    @Query("DELETE FROM earthquake")
    suspend fun deleteAll()


}