package com.example.quakewatch.data.source.network

import com.google.gson.annotations.SerializedName

data class NetworkEarthquakeProperty(
    @SerializedName("features")
    val networkEarthquakes: List<NetworkEarthquake>
)

data class NetworkEarthquake(
    @SerializedName("properties")
    val property : Property,
    @SerializedName("geometry")
    val geometry : Geometry,
    @SerializedName("id")
    val eventId : String
)

data class Property(
    @SerializedName("mag")
    val magnitude : Double,
    @SerializedName("place")
    val place : String,
    @SerializedName("time")
    val time : Long,
    @SerializedName("url")
    val url : String
)

data class Geometry(
    @SerializedName("coordinates")
    val coordinates : Array<Double>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Geometry

        if (!coordinates.contentEquals(other.coordinates)) return false

        return true
    }

    override fun hashCode(): Int {
        return coordinates.contentHashCode()
    }
}