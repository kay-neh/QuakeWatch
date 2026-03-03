package com.example.quakewatch.ui.screen.settings

import com.example.quakewatch.data.source.local.datastore.SortType

sealed interface SettingsEvent {
    object ShowSortTypeDialog : SettingsEvent
    object HideSortTypeDialog : SettingsEvent
    data class UpdateSortType(val sortType: SortType) : SettingsEvent
}