package com.example.quakewatch.data.remote

import com.example.quakewatch.data.remote.model.EarthquakeResponse
import retrofit2.Response
import javax.inject.Inject

class QuakeWatchRemoteDataSource @Inject constructor(
    private val quakeWatchService: QuakeWatchService
) {
    suspend fun getEarthquakeResponse() : Response<EarthquakeResponse> {
        return quakeWatchService.getEarthquakeResponse()
    }
}