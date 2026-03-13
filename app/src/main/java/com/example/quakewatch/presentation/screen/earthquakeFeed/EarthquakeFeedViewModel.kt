package com.example.quakewatch.presentation.screen.earthquakeFeed

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quakewatch.domain.usecase.QuakeWatchUseCases
import com.example.quakewatch.presentation.mapper.toEarthquakeFeedList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EarthquakeFeedViewModel @Inject constructor(
    private val quakeWatchUseCases: QuakeWatchUseCases
) : ViewModel() {

    private val _earthquakes = quakeWatchUseCases.getSortedEarthquakes()
        .map { earthquakes ->
            earthquakes.toEarthquakeFeedList()
        }
    private val _state = MutableStateFlow(EarthquakeFeedUIState())
    val state = combine(_earthquakes, _state) { earthquakes, state ->
        state.copy(
            earthquakes = earthquakes
        )
    }


    init {
        Log.d("EarthquakeFeedViewModel", "ViewModel initialized")
        refreshEarthquakes()
    }

    private fun refreshEarthquakes() {
        viewModelScope.launch {
            quakeWatchUseCases.refreshEarthquake()
        }
    }

}