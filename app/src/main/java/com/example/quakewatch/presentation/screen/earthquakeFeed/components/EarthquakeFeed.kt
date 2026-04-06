package com.example.quakewatch.presentation.screen.earthquakeFeed.components

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
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.foundation.text.TextAutoSizeDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quakewatch.presentation.screen.earthquakeFeed.EarthquakeFeed
import com.example.quakewatch.presentation.screen.earthquakeFeed.EarthquakeFeedUIState
import com.example.quakewatch.presentation.util.ScrollToTopController
import com.example.quakewatch.presentation.util.getMagnitudeColor


@Composable
fun EarthquakeFeed(
    modifier: Modifier = Modifier,
    state: EarthquakeFeedUIState,
    onItemClick: (String) -> Unit
) {

    val listState = rememberLazyListState()

    LaunchedEffect(true) {
        ScrollToTopController.events.collect {
            listState.animateScrollToItem(index = 0)
        }
    }


    LazyColumn(
        modifier = modifier
            .clipToBounds(),
        state = listState
    ) {
        items(
            items = state.earthquakes,
            key = { earthquakeFeed -> earthquakeFeed.eventId }
        ) { earthquakeFeed ->
            EarthquakeFeedItem(
                modifier = Modifier.animateItem(),
                context = LocalContext.current,
                earthquakeFeed = earthquakeFeed,
                onClick = { onItemClick(earthquakeFeed.eventId) }
            )
        }
    }
}

@Composable
fun EarthquakeFeedItem(
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
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = earthquakeFeed.magnitude,
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
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Column(
                    modifier = modifier
                        .height(50.dp)
                        .fillMaxWidth(0.7f),
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
                        style = MaterialTheme.typography.bodyLarge,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
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
        location = "Erlands Point-Kitsap Lake, Washington",
        offset = "3 km SW of",
        date = "Apr 04, 2026",
        time = "10:10 AM"
    )
    EarthquakeFeedItem(
        context = LocalContext.current,
        earthquakeFeed = earthquakeFeed,
        onClick = {}
    )
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