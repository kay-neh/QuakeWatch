package com.example.quakewatch.presentation.screen.settings

import com.example.quakewatch.domain.model.UserPreference

data class SettingsUIState(
    val userPreference: UserPreference = UserPreference(),
    val isUpdatingSortType: Boolean = false

)
