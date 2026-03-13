package com.example.quakewatch.data.source.local.datastore

import androidx.datastore.core.Serializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

class AppPreferenceSerializer : Serializer<AppPreference> {
    override suspend fun readFrom(input: InputStream): AppPreference {
        return try {
            Json.decodeFromString(
                deserializer = AppPreference.serializer(),
                string = input.readBytes().decodeToString()
            )
        } catch (e: SerializationException) {
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(
        t: AppPreference,
        output: OutputStream
    ) {
        output.write(
            Json.encodeToString(
                serializer = AppPreference.serializer(),
                value = t
            ).encodeToByteArray()
        )
    }

    override val defaultValue: AppPreference
        get() = AppPreference()
}