package com.example.quakewatch.ui.screen.earthquakeDetail

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class EarthquakeDetailViewModel(
    private val eventId: String,
) : ViewModel() {

    val _state = MutableStateFlow(EarthquakeDetailUIState(eventId))
    val state = _state.asStateFlow()


}