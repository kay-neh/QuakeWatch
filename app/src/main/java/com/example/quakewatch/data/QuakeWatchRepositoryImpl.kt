package com.example.quakewatch.data

import android.util.Log
import com.example.quakewatch.data.local.QuakeWatchLocalDataSource
import com.example.quakewatch.data.local.model.Earthquake
import com.example.quakewatch.data.remote.QuakeWatchRemoteDataSource
import com.example.quakewatch.data.remote.model.EarthquakeResponse
import com.example.quakewatch.data.remote.model.Feature
import com.example.quakewatch.domain.QuakeWatchRepository
import javax.inject.Inject

class QuakeWatchRepositoryImpl @Inject constructor(
    val remoteDataSource: QuakeWatchRemoteDataSource,
    val localDataSource: QuakeWatchLocalDataSource
): QuakeWatchRepository {
    override suspend fun upsertEarthquake() {
        val response = remoteDataSource.getEarthquakeResponse()
        if(response.isSuccessful) {
            response.body()!!.features.forEach {
                Log.d("Response",featureAsEarthquake(it).toString())
            }
            localDataSource.upsertEarthquakes(earthquakeResponseAsDTO(response.body()!!))
        }
    }

//    suspend fun getResponse() {
//        val response = remoteDataSource.getEarthquakeResponse()
//        if(response.isSuccessful) {
//                //println(earthquakeResponseAsDTO(response.body()!!))
//            localDataSource.upsertEarthquakes(earthquakeResponseAsDTO(response.body()!!))
//        }
//    }

    private fun earthquakeResponseAsDTO(earthquakeResponse: EarthquakeResponse): List<Earthquake> {
        return earthquakeResponse.features.map {
            featureAsEarthquake(it)
        }
    }

    private fun featureAsEarthquake(feature: Feature): Earthquake {
        return Earthquake(
            eventId = feature.eventId,
            magnitude = feature.property.magnitude,
            place = feature.property.place,
            time = feature.property.time,
            url = feature.property.url,
            latitude = feature.geometry.coordinates[0],
            longitude = feature.geometry.coordinates[1],
            depth = feature.geometry.coordinates[2]
        )
    }

}