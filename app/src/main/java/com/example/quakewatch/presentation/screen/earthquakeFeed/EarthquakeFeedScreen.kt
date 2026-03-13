package com.example.quakewatch.presentation.screen.earthquakeFeed

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.quakewatch.R
import com.example.quakewatch.presentation.screen.earthquakeFeed.components.EarthquakeFeed
import com.example.quakewatch.presentation.screen.earthquakeFeed.components.EarthquakeFeedItem
import com.example.quakewatch.presentation.util.getMagnitudeColor
import kotlin.math.floor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EarthquakeFeedScreen(
    modifier: Modifier = Modifier,
    viewModel: EarthquakeFeedViewModel,
    onNavigate: () -> Unit,
    onItemClick: (String) -> Unit
) {
    val scrollBehaviour = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val state by viewModel.state.collectAsStateWithLifecycle(EarthquakeFeedUIState())

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