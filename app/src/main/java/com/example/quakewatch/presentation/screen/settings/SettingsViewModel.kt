package com.example.quakewatch.presentation.screen.settings

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quakewatch.domain.model.UserPreference
import com.example.quakewatch.domain.usecase.UserPreferenceUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val userPreferenceUseCases: UserPreferenceUseCases
) : ViewModel() {

    private val _userPreference = userPreferenceUseCases.getUserPreference()
    private val _state = MutableStateFlow(SettingsUIState())
    val state = combine(_userPreference, _state)
    { userPreference, state ->
        state.copy(
            userPreference = userPreference
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), SettingsUIState())


    fun onEvent(event: SettingsEvent) {
        when (event) {
            SettingsEvent.HideSortTypeDialog -> {
                _state.update {
                    it.copy(
                        isUpdatingSortType = false
                    )
                }
            }

            SettingsEvent.ShowSortTypeDialog -> {
                _state.update {
                    it.copy(
                        isUpdatingSortType = true
                    )
                }
            }

            is SettingsEvent.UpdateSortType -> {
                viewModelScope.launch {
                    if (event.sortType != state.value.userPreference.sortType) {
                        Log.d("DataStore", "preference updated")
                        userPreferenceUseCases.updateSortTypePreference(event.sortType)
                    }
                }
            }
        }
    }

}