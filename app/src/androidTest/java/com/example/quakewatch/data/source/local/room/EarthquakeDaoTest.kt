package com.example.quakewatch.data.source.local.room

import com.example.quakewatch.di.AppModule
import com.example.quakewatch.di.RepositoryModule
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
@UninstallModules(AppModule::class, RepositoryModule::class)
class EarthquakeDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var database: QuakeWatchDatabase
    private lateinit var dao: EarthquakeDao

    @Before
    fun setUp() {
        hiltRule.inject()
        dao = database.dao
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun upsertAllEarthquakes() = runTest {
        val localEarthquakes = listOf(
            LocalEarthquake(
                eventId = "1",
                magnitude = 5.0,
                place = "place",
                time = 1L,
                url = "url",
                longitude = 1.2,
                latitude = 1.3,
                depth = 1.5
            ),
            LocalEarthquake(
                eventId = "2",
                magnitude = 3.0,
                place = "place",
                time = 2L,
                url = "url",
                longitude = 1.5,
                latitude = 1.3,
                depth = 1.2
            ),
        )
        dao.upsertAll(localEarthquakes)
        val result = dao.observeById("1").first()

        assertThat(result).isEqualTo(localEarthquakes[0])
    }

    @Test
    fun observeEarthquakesByTime() = runTest {
        val localEarthquakes = listOf(
            LocalEarthquake(
                eventId = "1",
                magnitude = 5.0,
                place = "place",
                time = 1L,
                url = "url",
                longitude = 1.2,
                latitude = 1.3,
                depth = 1.5
            ),
            LocalEarthquake(
                eventId = "2",
                magnitude = 3.0,
                place = "place",
                time = 2L,
                url = "url",
                longitude = 1.5,
                latitude = 1.3,
                depth = 1.2
            ),
            LocalEarthquake(
                eventId = "3",
                magnitude = 2.0,
                place = "place",
                time = 3L,
                url = "url",
                longitude = 1.5,
                latitude = 1.3,
                depth = 1.2
            ),
        )
        dao.upsertAll(localEarthquakes)
        val result = dao.observeAllByTime().first()
        val expectedResult = localEarthquakes.sortedByDescending { it.time }

        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    fun observeEarthquakesByMagnitude() = runTest {
        val localEarthquakes = listOf(
            LocalEarthquake(
                eventId = "1",
                magnitude = 5.0,
                place = "place",
                time = 1L,
                url = "url",
                longitude = 1.2,
                latitude = 1.3,
                depth = 1.5
            ),
            LocalEarthquake(
                eventId = "2",
                magnitude = 3.0,
                place = "place",
                time = 2L,
                url = "url",
                longitude = 1.5,
                latitude = 1.3,
                depth = 1.2
            ),
            LocalEarthquake(
                eventId = "3",
                magnitude = 2.0,
                place = "place",
                time = 3L,
                url = "url",
                longitude = 1.5,
                latitude = 1.3,
                depth = 1.2
            ),
        )
        dao.upsertAll(localEarthquakes)
        val result = dao.observeAllByMagnitude().first()
        val expectedResult = localEarthquakes.sortedByDescending { it.magnitude }

        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    fun observeEarthquakeById() = runTest {
        val localEarthquakes = listOf(
            LocalEarthquake(
                eventId = "1",
                magnitude = 5.0,
                place = "place",
                time = 1L,
                url = "url",
                longitude = 1.2,
                latitude = 1.3,
                depth = 1.5
            ),
            LocalEarthquake(
                eventId = "2",
                magnitude = 3.0,
                place = "place",
                time = 2L,
                url = "url",
                longitude = 1.5,
                latitude = 1.3,
                depth = 1.2
            ),
            LocalEarthquake(
                eventId = "3",
                magnitude = 2.0,
                place = "place",
                time = 3L,
                url = "url",
                longitude = 1.5,
                latitude = 1.3,
                depth = 1.2
            ),
        )
        dao.upsertAll(localEarthquakes)
        val result = dao.observeById("2").first()
        val expectedResult = localEarthquakes.find { it.eventId == "2" }

        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    fun deleteAllEarthquakes() = runTest {
        val localEarthquakes = listOf(
            LocalEarthquake(
                eventId = "1",
                magnitude = 5.0,
                place = "place",
                time = 1L,
                url = "url",
                longitude = 1.2,
                latitude = 1.3,
                depth = 1.5
            ),
            LocalEarthquake(
                eventId = "2",
                magnitude = 3.0,
                place = "place",
                time = 2L,
                url = "url",
                longitude = 1.5,
                latitude = 1.3,
                depth = 1.2
            ),
            LocalEarthquake(
                eventId = "3",
                magnitude = 2.0,
                place = "place",
                time = 3L,
                url = "url",
                longitude = 1.5,
                latitude = 1.3,
                depth = 1.2
            ),
        )
        dao.upsertAll(localEarthquakes)
        dao.deleteAll()
        val result = dao.observeAllByTime().first()

        assertThat(result).isEqualTo(emptyList<LocalEarthquake>())
    }

}