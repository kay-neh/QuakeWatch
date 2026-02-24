package com.example.quakewatch.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [LocalEarthquake::class],
    version = 1
)
abstract class QuakeWatchDatabase : RoomDatabase() {

    abstract val dao: EarthquakeDao
}