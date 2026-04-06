package com.example.quakewatch.presentation.screen.earthquakeFeed

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quakewatch.domain.usecase.QuakeWatchUseCases
import com.example.quakewatch.domain.util.NetworkError
import com.example.quakewatch.domain.util.Result
import com.example.quakewatch.presentation.mapper.toEarthquakeFeedList
import com.example.quakewatch.presentation.util.ScrollToTopController
import com.example.quakewatch.presentation.util.SnackBarController
import com.example.quakewatch.presentation.util.SnackBarEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
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
            when (val refresh = quakeWatchUseCases.refreshEarthquake()) {
                is Result.Error -> {
                    when (refresh.error) {
                        NetworkError.API_ERROR -> {
                            Log.e("Viewmodel Error", "API_ERROR")
                        }

                        NetworkError.NETWORK_ERROR -> {
                            SnackBarController.sendEvent(
                                event = SnackBarEvent(
                                    message = "No internet Connection"
                                )
                            )
                            Log.e("Viewmodel Error", "NETWORK_ERROR")
                        }

                        NetworkError.UNKNOWN_ERROR -> {
                            Log.e("Viewmodel Error", "UNKNOWN_ERROR")
                        }
                    }
                }

                is Result.Success -> {
                    // show success SnackBar
                    SnackBarController.sendEvent(
                        event = SnackBarEvent(
                            message = "Refreshed Successfully"
                        )
                    )
                    ScrollToTopController.sendEvent()
                    Log.e("Viewmodel Success", "Success")
                }
            }
        }
    }

}