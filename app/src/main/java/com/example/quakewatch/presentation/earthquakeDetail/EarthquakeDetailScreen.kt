package com.example.quakewatch.presentation.earthquakeDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun EarthquakeDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: EarthquakeDetailViewModel
) {
    val state by viewModel.state.collectAsStateWithLifecycle(EarthquakeDetailUIState())
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Text(text = state.earthquakeDetail.eventId)
    }
}