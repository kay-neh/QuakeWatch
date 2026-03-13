package com.example.quakewatch.presentation.screen.earthquakeFeed

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
        EarthquakeList(
            modifier = modifier.padding(it),
            state = state,
            onItemClick = { eventId ->
                onItemClick(eventId)
            }
        )
    }
}

@Composable
fun EarthquakeList(
    modifier: Modifier = Modifier,
    state: EarthquakeFeedUIState,
    onItemClick: (String) -> Unit
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(state.earthquakes) { earthquakeFeed ->
            EarthquakeItem(
                context = LocalContext.current,
                earthquakeFeed = earthquakeFeed,
                onClick = { onItemClick(earthquakeFeed.eventId) }
            )
        }
    }
}

@Composable
fun EarthquakeItem(
    modifier: Modifier = Modifier,
    context: Context,
    earthquakeFeed: EarthquakeFeed,
    onClick: (String) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .clickable { onClick(earthquakeFeed.eventId) }
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(13.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Text(
                    modifier = modifier
                        .clip(CircleShape)
                        .background(
                            color = Color(
                                getMagnitudeColor(
                                    context,
                                    earthquakeFeed.magnitude.toDouble()
                                )
                            )
                        )
                        .padding(16.dp),
                    text = earthquakeFeed.magnitude,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Spacer(modifier = modifier.width(16.dp))
                Column(
                    modifier = modifier.height(50.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = earthquakeFeed.offset,
                        modifier = modifier.padding(bottom = 0.dp),
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )
                    Text(
                        text = earthquakeFeed.location,
                        fontSize = 16.sp,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
            Column(
                modifier = modifier.height(50.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = earthquakeFeed.date,
                    modifier = modifier.padding(bottom = 4.dp),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
                Text(
                    text = earthquakeFeed.time,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }
        }

        HorizontalDivider(
            modifier = modifier.padding(start = 85.dp, end = 14.dp),
            thickness = 2.dp
        )
    }
}

@Composable
fun EarthquakeItemPreview(modifier: Modifier = Modifier) {
    val earthquakeFeed = EarthquakeFeed(
        eventId = "12345",
        magnitude = "2.5",
        location = "California",
        offset = "NEAR THE",
        date = "10/10/2023",
        time = "10:10 AM"
    )
    EarthquakeItem(
        context = LocalContext.current,
        earthquakeFeed = earthquakeFeed,
        onClick = {}
    )
}

fun getMagnitudeColor(context: Context, magnitude: Double): Int {
    val magnitudeColorResourceId: Int
    val magnitudeFloor = floor(magnitude).toInt()
    magnitudeColorResourceId = when (magnitudeFloor) {
        0, 1 -> R.color.magnitude1
        2 -> R.color.magnitude2
        3 -> R.color.magnitude3
        4 -> R.color.magnitude4
        5 -> R.color.magnitude5
        6 -> R.color.magnitude6
        7 -> R.color.magnitude7
        8 -> R.color.magnitude8
        9 -> R.color.magnitude9
        else -> R.color.magnitude10plus
    }
    return ContextCompat.getColor(context, magnitudeColorResourceId)
}

@Preview
@Composable
private fun PreviewEarthquakeItem() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        EarthquakeItemPreview()
    }
}