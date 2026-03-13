package com.example.quakewatch.domain.model

data class UserPreference(
    var sortType: SortType = SortType.BY_TIME,
    var isDarkTheme: Boolean = false
)

enum class SortType {
    BY_TIME,
    BY_MAGNITUDE
}