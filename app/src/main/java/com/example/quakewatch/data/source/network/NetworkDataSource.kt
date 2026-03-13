package com.example.quakewatch.data.source.network

import retrofit2.Response

interface NetworkDataSource {

//    suspend fun loadEarthquakes(): Response<NetworkEarthquakeProperty>

    suspend fun loadEarthquakes(): List<NetworkEarthquake>
}