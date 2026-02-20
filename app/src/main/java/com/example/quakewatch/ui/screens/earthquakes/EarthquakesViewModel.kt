package com.example.quakewatch.ui.screens.earthquakes

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quakewatch.domain.QuakeWatchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EarthquakesViewModel @Inject constructor(
    val repository: QuakeWatchRepository
) : ViewModel() {

    init {
        //upsertEarthquakes()
        Log.d("EarthquakesViewModel", "ViewModel initialized")
    }

    fun upsertEarthquakes() {
        viewModelScope.launch {
            repository.upsertEarthquakes()
        }
    }

    fun getEarthquakesStream() {


    }

    fun deleteAll() {
        viewModelScope.launch {
            repository.deleteEarthquakes()
        }

    }

}