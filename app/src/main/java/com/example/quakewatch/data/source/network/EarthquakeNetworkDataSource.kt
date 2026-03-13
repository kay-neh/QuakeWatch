package com.example.quakewatch.data.source.network

import retrofit2.Response
import javax.inject.Inject

class EarthquakeNetworkDataSource @Inject constructor(
    private val quakeWatchService: QuakeWatchService
) : NetworkDataSource {

//    override suspend fun loadEarthquakes(): Response<NetworkEarthquakeProperty> {
//        return quakeWatchService.loadEarthquakes()
//    }

    override suspend fun loadEarthquakes(): List<NetworkEarthquake> {
        val response = quakeWatchService.loadEarthquakes()
        if (response.isSuccessful) {
            return response.body()?.networkEarthquakes ?: emptyList()
        }
        return emptyList()
    }
}