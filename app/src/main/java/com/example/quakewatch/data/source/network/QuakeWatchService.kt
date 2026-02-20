package com.example.quakewatch.data.source.network

import retrofit2.Response
import retrofit2.http.GET

interface QuakeWatchService {
    @GET("query?format=geojson&orderby=time&limit=50")
    suspend fun loadEarthquakes(): Response<NetworkEarthquakeProperty>
}