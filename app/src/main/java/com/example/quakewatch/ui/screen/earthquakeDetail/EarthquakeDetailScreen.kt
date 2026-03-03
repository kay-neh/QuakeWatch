package com.example.quakewatch.ui.screen.earthquakeDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun EarthquakeDetailScreen(
    modifier: Modifier = Modifier,
    eventId: String,
    viewModel: EarthquakeDetailViewModel = viewModel {
        EarthquakeDetailViewModel(eventId)
    }
) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Text(text = state.value.eventId)
    }
}