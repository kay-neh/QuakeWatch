package com.example.quakewatch.data.source.local.datastore

import kotlinx.serialization.Serializable

@Serializable
data class UserPreference(
    var sortType: SortType = SortType.BY_TIME,
)

enum class SortType {
    BY_TIME,
    BY_MAGNITUDE
}


