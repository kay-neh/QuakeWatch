package com.example.quakewatch.presentation.settings

import com.example.quakewatch.data.source.local.datastore.SortType

sealed interface SettingsEvent {
    object ShowSortTypeDialog : SettingsEvent
    object HideSortTypeDialog : SettingsEvent
    data class UpdateSortType(val sortType: SortType) : SettingsEvent
}