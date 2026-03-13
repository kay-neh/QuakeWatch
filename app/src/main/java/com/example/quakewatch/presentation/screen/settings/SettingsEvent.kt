package com.example.quakewatch.presentation.screen.settings

import com.example.quakewatch.domain.model.SortType

sealed interface SettingsEvent {
    object ShowSortTypeDialog : SettingsEvent
    object HideSortTypeDialog : SettingsEvent
    data class UpdateSortType(val sortType: SortType) : SettingsEvent
}