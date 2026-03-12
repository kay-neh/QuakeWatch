package com.example.quakewatch.domain.usecase

import com.example.quakewatch.data.FakeRepository
import com.example.quakewatch.data.source.local.datastore.SortType
import com.example.quakewatch.data.source.local.datastore.UserPreference
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class UpdateSortTypePreferenceTest {

    private lateinit var fakeRepository: FakeRepository
    private lateinit var updateSortTypePreference: UpdateSortTypePreference

    @Before
    fun setUp() {
        fakeRepository = FakeRepository()
        updateSortTypePreference = UpdateSortTypePreference(fakeRepository)
    }

    @Test
    fun `Set user preference with correct sortType, returns true`() = runBlocking {
        updateSortTypePreference(SortType.BY_MAGNITUDE)
        val result = fakeRepository.getUserPreference().first().sortType
        val expectedResult = SortType.BY_MAGNITUDE

        assertThat(result).isEqualTo(expectedResult)
    }


}