package com.example.quakewatch.presentation.screen.earthquakeFeed

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.quakewatch.R
import com.example.quakewatch.presentation.screen.earthquakeFeed.components.EarthquakeFeed
import com.example.quakewatch.presentation.screen.earthquakeFeed.components.EarthquakeItemPreview

@Composable
fun EarthquakeFeedScreen(
    modifier: Modifier = Modifier,
    viewModel: EarthquakeFeedViewModel,
    onNavigate: () -> Unit,
    onItemClick: (String) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle(EarthquakeFeedUIState())
    EarthquakeFeedScreen(
        modifier = modifier,
        state = state,
        onNavigate = onNavigate,
        onItemClick = onItemClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EarthquakeFeedScreen(
    modifier: Modifier = Modifier,
    state: EarthquakeFeedUIState,
    onNavigate: () -> Unit,
    onItemClick: (String) -> Unit
) {

    val scrollBehaviour = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehaviour.nestedScrollConnection),
//        contentWindowInsets = WindowInsets.statusBars,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "QuakeWatch")
                },
                actions = {
                    IconButton(onClick = { onNavigate() }) {
                        Icon(painterResource(R.drawable.settings_24px), contentDescription = null)
                    }
                },
                scrollBehavior = scrollBehaviour
            )
        }
    ) {
        EarthquakeFeed(
            modifier = modifier.padding(it),
            state = state,
            onItemClick = { eventId ->
                onItemClick(eventId)
            }
        )
    }

}

@Preview
@Composable
private fun PreviewEarthquakeItem() {

    val list = mutableListOf<EarthquakeFeed>()
    for (i in 1..15) {
        list.add(
            EarthquakeFeed(
                eventId = "$i",
                magnitude = "3.5",
                location = "location $i",
                offset = "Shores of Alaska",
                date = "1$i March 2026",
                time = "12:30am"
            )
        )
    }

    val state = EarthquakeFeedUIState(
        earthquakes = list
    )

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        EarthquakeFeedScreen(
            state = state,
            onNavigate = {},
            onItemClick = {}
        )
    }
}