package com.example.quakewatch.ui.screens.earthquake_list

import androidx.lifecycle.ViewModel
import com.example.quakewatch.domain.QuakeWatchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EarthquakeListViewModel @Inject constructor(
    val repository: QuakeWatchRepository
) : ViewModel() {

}