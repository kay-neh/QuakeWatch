package com.example.quakewatch.data.remote

import com.example.quakewatch.data.remote.model.EarthquakeResponse
import retrofit2.Response
import retrofit2.http.GET

interface QuakeWatchService {
    @GET("query?format=geojson&orderby=time&limit=50")
    suspend fun getEarthquakeResponse() : Response<EarthquakeResponse>
}