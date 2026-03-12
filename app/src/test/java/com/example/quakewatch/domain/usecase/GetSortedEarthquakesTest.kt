package com.example.quakewatch.domain.usecase

import com.example.quakewatch.data.FakeRepository
import com.example.quakewatch.data.source.local.datastore.SortType
import com.example.quakewatch.data.source.network.Geometry
import com.example.quakewatch.data.source.network.NetworkEarthquake
import com.example.quakewatch.data.source.network.Property
import com.example.quakewatch.data.toExternal
import com.example.quakewatch.data.toLocal
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetSortedEarthquakesTest {

    private lateinit var networkEarthquakesToInsert: MutableList<NetworkEarthquake>
    private lateinit var fakeRepository: FakeRepository
    private lateinit var getSortedEarthquakes: GetSortedEarthquakes

    @Before
    fun setUp() {
        fakeRepository = FakeRepository()
        getSortedEarthquakes = GetSortedEarthquakes(fakeRepository)

        // pre populate data
        // pre populate data
        networkEarthquakesToInsert = mutableListOf()
        networkEarthquakesToInsert.add(
            NetworkEarthquake(
                property = Property(
                    magnitude = 7.0,
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
                    magnitude = 5.0,
                    place = "place",
                    time = 2L,
                    url = "url"
                ),
                geometry = Geometry(
                    coordinates = arrayOf(1.0, 1.2, 1.5)
                ),
                eventId = "2"
            )
        )
        networkEarthquakesToInsert.add(
            NetworkEarthquake(
                property = Property(
                    magnitude = 2.0,
                    place = "place",
                    time = 3L,
                    url = "url"
                ),
                geometry = Geometry(
                    coordinates = arrayOf(1.0, 1.2, 1.5)
                ),
                eventId = "3"
            )
        )
        fakeRepository.setNetworkResponse(networkEarthquakesToInsert)
        runBlocking {
            fakeRepository.upsertEarthquakes(fakeRepository.loadEarthquakes().toLocal())
        }
    }

    @Test
    fun `get Earthquake sorted by descending time, returns true`() = runBlocking {
        fakeRepository.setSortType(SortType.BY_TIME)
        val result = getSortedEarthquakes().first()
        val expectedResult = networkEarthquakesToInsert.toLocal().toExternal().sortedByDescending {
            it.time
        }
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    fun `get Earthquake sorted by descending magnitude, returns true`() = runBlocking {
        fakeRepository.setSortType(SortType.BY_MAGNITUDE)
        val result = getSortedEarthquakes().first()
        val expectedResult = networkEarthquakesToInsert.toLocal().toExternal().sortedByDescending {
            it.magnitude
        }

        assertThat(result).isEqualTo(expectedResult)
    }

}