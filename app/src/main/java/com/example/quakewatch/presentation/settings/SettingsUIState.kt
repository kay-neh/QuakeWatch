package com.example.quakewatch.presentation.settings

import com.example.quakewatch.data.source.local.datastore.UserPreference

data class SettingsUIState(
    val userPreference: UserPreference = UserPreference(),
    val isUpdatingSortType: Boolean = false

)
