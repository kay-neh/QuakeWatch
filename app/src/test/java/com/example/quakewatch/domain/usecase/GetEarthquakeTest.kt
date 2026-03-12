package com.example.quakewatch.domain.usecase

import com.example.quakewatch.data.FakeRepository
import com.example.quakewatch.data.source.local.room.LocalEarthquake
import com.example.quakewatch.data.toExternal
import com.example.quakewatch.domain.repository.QuakeWatchRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetEarthquakeTest {

    private lateinit var fakeRepository: FakeRepository
    private lateinit var getEarthquake: GetEarthquake
    private lateinit var localEarthquakesToInsert: MutableList<LocalEarthquake>


    @Before
    fun setup() {
        fakeRepository = FakeRepository()
        getEarthquake = GetEarthquake(fakeRepository)

        // pre populate data
        localEarthquakesToInsert = mutableListOf<LocalEarthquake>()
        localEarthquakesToInsert.add(
            LocalEarthquake(
                eventId = "1",
                magnitude = 1.0,
                place = "place",
                time = 1L,
                url = "url",
                latitude = 1.0,
                longitude = 1.0,
                depth = 1.0
            )
        )
        localEarthquakesToInsert.add(
            LocalEarthquake(
                eventId = "2",
                magnitude = 1.0,
                place = "place",
                time = 1L,
                url = "url",
                latitude = 1.0,
                longitude = 1.0,
                depth = 1.0
            )
        )
        runBlocking {
            fakeRepository.upsertEarthquakes(localEarthquakesToInsert)
        }
    }

    @Test
    fun `Get earthquake by id returns correct earthquake`() = runBlocking {
        val earthquake = getEarthquake("1").first()
        assertThat(earthquake).isEqualTo(localEarthquakesToInsert[0].toExternal())
    }

}