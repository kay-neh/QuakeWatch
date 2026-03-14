package com.example.quakewatch.domain.usecase

import com.example.quakewatch.data.FakeRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetUserPreferenceTest {

    private lateinit var fakeRepository: FakeRepository
    private lateinit var getUserPreference: GetUserPreference

    @Before
    fun setUp() {
        fakeRepository = FakeRepository()
        getUserPreference = GetUserPreference(fakeRepository)
    }

    @Test
    fun `Get correct user preference with correct sortType, returns true`() = runBlocking {
        val result = getUserPreference().first()
        val expectedResult = fakeRepository.getUserPreference().first()

        assertThat(result).isEqualTo(expectedResult)
    }

}