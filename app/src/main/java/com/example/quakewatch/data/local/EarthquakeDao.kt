package com.example.quakewatch.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.quakewatch.data.local.model.Earthquake
import kotlinx.coroutines.flow.Flow

@Dao
interface EarthquakeDao {

    @Upsert
    suspend fun upsertEarthquakes(earthquakes: List<Earthquake>)

    @Query("SELECT * FROM earthquake")
    fun getEarthquakes(): Flow<List<Earthquake>>

    @Query("SELECT * FROM earthquake WHERE eventId = :eventId")
    suspend fun getEarthquakeById(eventId: String): Earthquake?

    @Query("DELETE FROM earthquake")
    suspend fun deleteEarthquakes()


}