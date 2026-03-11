package com.example.quakewatch.presentation.earthquakeDetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.example.quakewatch.domain.model.toEarthquakeDetail
import com.example.quakewatch.domain.usecase.QuakeWatchUseCases
import com.example.quakewatch.navigation.Route
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel(
    assistedFactory =
        EarthquakeDetailViewModel.Factory::class
)
class EarthquakeDetailViewModel @AssistedInject constructor(
    @Assisted val eventId: String,
    private val quakeWatchUseCases: QuakeWatchUseCases
) : ViewModel() {

    private val _earthquakeDetail = quakeWatchUseCases.getEarthquake(eventId).map {
        it.toEarthquakeDetail()
    }
    private val _state = MutableStateFlow(EarthquakeDetailUIState())
    val state = combine(_earthquakeDetail, _state) { earthquakeDetail, state ->
        state.copy(
            earthquakeDetail = earthquakeDetail
        )
    }


    @AssistedFactory
    interface Factory {
        fun create(eventId: String): EarthquakeDetailViewModel
    }

}