package com.example.quakewatch.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.quakewatch.data.local.model.Earthquake

@Database(
    entities = [Earthquake::class],
    version = 1
)
abstract class QuakeWatchDatabase : RoomDatabase() {

    abstract val dao: EarthquakeDao
}