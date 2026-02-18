package com.example.quakewatch.domain

import com.example.quakewatch.data.local.model.Earthquake

interface QuakeWatchRepository {

    suspend fun upsertEarthquake()

}