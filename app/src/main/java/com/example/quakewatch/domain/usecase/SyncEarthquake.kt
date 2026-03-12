package com.example.quakewatch.domain.usecase

import com.example.quakewatch.domain.repository.QuakeWatchRepository

class SyncEarthquake(
    private val quakeWatchRepository: QuakeWatchRepository
) {


}