package com.example.quakewatch.data.source.local.datastore

import com.example.quakewatch.domain.model.SortType
import kotlinx.serialization.Serializable

@Serializable
data class AppPreference(
    var sortType: SortType = SortType.BY_TIME,
    var isDarkTheme: Boolean = false
)

