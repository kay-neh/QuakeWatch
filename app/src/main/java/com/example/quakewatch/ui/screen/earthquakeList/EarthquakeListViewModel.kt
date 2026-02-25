package com.example.quakewatch.ui.screen.earthquakeList

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quakewatch.domain.usecase.QuakeWatchUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.collections.emptyList

@HiltViewModel
class EarthquakeListViewModel @Inject constructor(
    val quakeWatchUseCases: QuakeWatchUseCases
) : ViewModel() {

    val earthquakes = quakeWatchUseCases.getSortedEarthquakes()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    init {
        Log.d("EarthquakeListViewModel", "ViewModel initialized")
        refreshEarthquakes()
    }

    fun refreshEarthquakes() {
        viewModelScope.launch {
            quakeWatchUseCases.refreshEarthquake()
        }
    }

}