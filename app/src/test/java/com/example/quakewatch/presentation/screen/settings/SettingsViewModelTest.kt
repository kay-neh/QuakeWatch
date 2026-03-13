package com.example.quakewatch.presentation.screen.settings

import com.example.quakewatch.data.FakeRepository
import com.example.quakewatch.domain.model.SortType
import com.example.quakewatch.domain.usecase.GetUserPreference
import com.example.quakewatch.domain.usecase.UpdateSortTypePreference
import com.example.quakewatch.domain.usecase.UserPreferenceUseCases
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class SettingsViewModelTest {

    private lateinit var viewModel: SettingsViewModel
    private lateinit var userPreferenceUseCases: UserPreferenceUseCases

    @Before
    fun setup() {
        userPreferenceUseCases = UserPreferenceUseCases(
            getUserPreference = GetUserPreference(FakeRepository()),
            updateSortTypePreference = UpdateSortTypePreference(FakeRepository())
        )
        viewModel = SettingsViewModel(userPreferenceUseCases)
    }

    @Test
    fun `update sort type preference, returns true`() {
        viewModel.onEvent(SettingsEvent.UpdateSortType(SortType.BY_TIME))

        assertThat(viewModel.state.value.userPreference.sortType).isEqualTo(SortType.BY_TIME)
    }

}