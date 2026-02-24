package com.example.quakewatch.ui.screen.earthquakes

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quakewatch.data.source.local.datastore.SortType
import com.example.quakewatch.domain.model.Earthquake
import com.example.quakewatch.domain.usecase.QuakeWatchUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.collections.emptyList

@HiltViewModel
class EarthquakesViewModel @Inject constructor(
    val quakeWatchUseCases: QuakeWatchUseCases
) : ViewModel() {

    val earthquakes = quakeWatchUseCases.getSortedEarthquakes()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    init {
        Log.d("EarthquakesViewModel", "ViewModel initialized")
        refreshEarthquakes()
    }

    fun refreshEarthquakes() {
        viewModelScope.launch {
            quakeWatchUseCases.refreshEarthquake()
        }
    }

}