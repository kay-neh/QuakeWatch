package com.example.quakewatch.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "earthquake")
data class Earthquake(
    @PrimaryKey(autoGenerate = false)
    val eventId : String,
    val magnitude : Double,
    val place : String,
    val time : Long,
    val url : String,
    val longitude : Double,
    val latitude : Double,
    val depth : Double
)
