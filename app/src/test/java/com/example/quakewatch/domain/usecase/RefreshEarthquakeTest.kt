package com.example.quakewatch.domain.usecase

import com.example.quakewatch.data.FakeRepository
import com.example.quakewatch.data.source.network.Geometry
import com.example.quakewatch.data.source.network.NetworkEarthquake
import com.example.quakewatch.data.source.network.Property
import com.example.quakewatch.data.mapper.toLocal
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class RefreshEarthquakeTest {

    private lateinit var networkEarthquakesToInsert: MutableList<NetworkEarthquake>
    private lateinit var fakeRepository: FakeRepository
    private lateinit var refreshEarthquake: RefreshEarthquake


    @Before
    fun setup() {
        fakeRepository = FakeRepository()
        refreshEarthquake = RefreshEarthquake(fakeRepository)

        // pre populate data
        networkEarthquakesToInsert = mutableListOf()
        networkEarthquakesToInsert.add(
            NetworkEarthquake(
                property = Property(
                    magnitude = 1.0,
                    place = "place",
                    time = 1L,
                    url = "url"
                ),
                geometry = Geometry(
                    coordinates = arrayOf(1.0, 1.2, 1.5)
                ),
                eventId = "1"
            )
        )
        networkEarthquakesToInsert.add(
            NetworkEarthquake(
                property = Property(
                    magnitude = 1.0,
                    place = "place",
                    time = 1L,
                    url = "url"
                ),
                geometry = Geometry(
                    coordinates = arrayOf(1.0, 1.2, 1.5)
                ),
                eventId = "2"
            )
        )
        fakeRepository.setNetworkResponse(networkEarthquakesToInsert)
    }

    @Test
    fun `Get earthquake from network, returns same response`() = runBlocking {
        refreshEarthquake()
        val result = fakeRepository.loadEarthquakes()

        assertThat(result).isEqualTo(networkEarthquakesToInsert)
    }

    @Test
    fun `Get earthquake from network and save to db returns valid earthquake`() = runBlocking {
        refreshEarthquake()
        val result = fakeRepository.getLocalResponses().first()

        assertThat(result).isEqualTo(networkEarthquakesToInsert.toLocal())
    }

}