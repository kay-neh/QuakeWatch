package com.example.quakewatch.data.source.network

import retrofit2.Response
import retrofit2.http.GET

interface QuakeWatchService {
    @GET("query?format=geojson&limit=100")
    suspend fun loadEarthquakes(): Response<NetworkEarthquakeProperty>
}