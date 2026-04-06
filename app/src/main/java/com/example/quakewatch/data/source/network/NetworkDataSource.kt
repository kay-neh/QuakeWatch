package com.example.quakewatch.data.source.network

import com.example.quakewatch.domain.util.Error
import com.example.quakewatch.domain.util.NetworkError
import com.example.quakewatch.domain.util.Result
import retrofit2.Response

interface NetworkDataSource {

    suspend fun loadEarthquakes(): Result<List<NetworkEarthquake>, NetworkError>
}

