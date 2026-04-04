package com.example.quakewatch.presentation.screen.earthquakeDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.quakewatch.R
import com.example.quakewatch.presentation.screen.earthquakeDetail.components.BottomSheetContent
import com.example.quakewatch.presentation.screen.earthquakeDetail.components.MapView
import com.example.quakewatch.presentation.screen.earthquakeFeed.EarthquakeFeedScreen

@Composable
fun EarthquakeDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: EarthquakeDetailViewModel,
    onNavigate: (String) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle(EarthquakeDetailUIState())
    EarthquakeDetailScreen(
        state = state,
        onNavigate = onNavigate
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EarthquakeDetailScreen(
    modifier: Modifier = Modifier,
    state: EarthquakeDetailUIState,
    onNavigate: (String) -> Unit
) {
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            initialValue = SheetValue.Expanded, // This is the key line
            skipHiddenState = false // Optional: decides if the sheet can be fully hidden
        )
    )
    BottomSheetScaffold(
        modifier = modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        sheetContent = {
            BottomSheetContent(earthquakeDetail = state.earthquakeDetail)
        }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // 1. The Map Layer
            MapView(earthquakeDetail = state.earthquakeDetail)

            // 2. The Navigation Icon Layer
            SmallFloatingActionButton(
                onClick = { onNavigate(state.earthquakeDetail.eventId) },
                modifier = Modifier
                    .padding(16.dp)
                    .statusBarsPadding()
                    .align(Alignment.TopStart), // Positions it at the top right
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.arrow_back_24px),
                    contentDescription = "Navigate"
                )
            }
        }


    }

}


@Preview
@Composable
private fun EarthquakeDetailScreenPreview() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        val state = EarthquakeDetailUIState(
            EarthquakeDetail(
                eventId = "1",
                magnitude = "7.2",
                place = "Off the coast of Japan",
                time = "Origin time: Feb 27, 2026, 14:11 UTC",
                url = "url",
                longitude = 103.4,
                latitude = 23.4,
                depth = 1.5,
                markerLocation = null
            )
        )

        EarthquakeDetailScreen(
            state = state,
            onNavigate = {}
        )
    }
}