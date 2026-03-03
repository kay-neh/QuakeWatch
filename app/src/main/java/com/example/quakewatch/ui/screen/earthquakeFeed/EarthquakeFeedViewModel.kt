package com.example.quakewatch.ui.screen.earthquakeFeed

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quakewatch.domain.model.toEarthquakeFeedList
import com.example.quakewatch.domain.usecase.QuakeWatchUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.collections.emptyList

@HiltViewModel
class EarthquakeFeedViewModel @Inject constructor(
    val quakeWatchUseCases: QuakeWatchUseCases
) : ViewModel() {

    val earthquakes = quakeWatchUseCases.getSortedEarthquakes()
        .map { earthquakes ->
            earthquakes.toEarthquakeFeedList()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )


    init {
        Log.d("EarthquakeFeedViewModel", "ViewModel initialized")
        refreshEarthquakes()
    }

    fun refreshEarthquakes() {
        viewModelScope.launch {
            quakeWatchUseCases.refreshEarthquake()
        }
    }

}