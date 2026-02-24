package com.example.quakewatch.data

import com.example.quakewatch.data.source.local.room.LocalEarthquake
import com.example.quakewatch.data.source.network.NetworkEarthquake
import com.example.quakewatch.domain.model.Earthquake

// Network DTO to Local DTO
fun NetworkEarthquake.toLocal() =
    LocalEarthquake(
        eventId = eventId,
        magnitude = property.magnitude,
        place = property.place,
        time = property.time,
        url = property.url,
        latitude = geometry.coordinates[0],
        longitude = geometry.coordinates[1],
        depth = geometry.coordinates[2]
    )

fun List<NetworkEarthquake>.toLocal() =
    map {
        it.toLocal()
    }


// Local DTO to External DTO
fun LocalEarthquake.toExternal() =
    Earthquake(
        eventId = eventId,
        magnitude = magnitude,
        place = place,
        time = time,
        url = url,
        latitude = latitude,
        longitude = longitude,
        depth = depth
    )

fun List<LocalEarthquake>.toExternal() =
    map {
        it.toExternal()
    }

