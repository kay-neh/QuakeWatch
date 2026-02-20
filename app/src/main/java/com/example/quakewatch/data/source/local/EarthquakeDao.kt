package com.example.quakewatch.data.source.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface EarthquakeDao {

    @Upsert
    suspend fun upsertAll(localEarthquakes: List<LocalEarthquake>)

    @Query("SELECT * FROM earthquake")
    fun observeAll(): Flow<List<LocalEarthquake>>

    @Query("SELECT * FROM earthquake WHERE eventId = :eventId")
    fun observeById(eventId: String): Flow<LocalEarthquake>

    @Query("SELECT * FROM earthquake")
    suspend fun getAll(): List<LocalEarthquake>

    @Query("SELECT * FROM earthquake WHERE eventId = :eventId")
    suspend fun getById(eventId: String): LocalEarthquake?

    @Query("DELETE FROM earthquake")
    suspend fun deleteAll()


}