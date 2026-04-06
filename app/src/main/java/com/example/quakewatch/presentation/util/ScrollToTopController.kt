package com.example.quakewatch.presentation.util

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

object ScrollToTop

object ScrollToTopController {

    private val _events = MutableSharedFlow<ScrollToTop>()
    val events = _events.asSharedFlow()

    suspend fun sendEvent() {
        _events.emit(ScrollToTop)
    }

}