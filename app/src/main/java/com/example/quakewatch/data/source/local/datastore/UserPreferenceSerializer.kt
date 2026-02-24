package com.example.quakewatch.data.source.local.datastore

import androidx.datastore.core.Serializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

class UserPreferenceSerializer : Serializer<UserPreference> {
    override suspend fun readFrom(input: InputStream): UserPreference {
        return try {
            Json.decodeFromString(
                deserializer = UserPreference.serializer(),
                string = input.readBytes().decodeToString()
            )
        } catch (e: SerializationException) {
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(
        t: UserPreference,
        output: OutputStream
    ) {
        output.write(
            Json.encodeToString(
                serializer = UserPreference.serializer(),
                value = t
            ).encodeToByteArray()
        )
    }

    override val defaultValue: UserPreference
        get() = UserPreference()
}