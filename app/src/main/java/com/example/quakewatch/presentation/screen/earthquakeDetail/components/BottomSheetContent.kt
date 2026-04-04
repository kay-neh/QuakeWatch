package com.example.quakewatch.presentation.screen.earthquakeDetail.components

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.example.quakewatch.R
import com.example.quakewatch.presentation.screen.earthquakeDetail.EarthquakeDetail
import com.example.quakewatch.presentation.util.formatPointCoordinates
import com.example.quakewatch.presentation.util.getMagnitudeColor
import androidx.core.net.toUri


@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun BottomSheetContent(
    modifier: Modifier = Modifier,
    earthquakeDetail: EarthquakeDetail,
) {

    val context = LocalContext.current
    val url = earthquakeDetail.url

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = earthquakeDetail.magnitude + " M",
                color = Color(
                    getMagnitudeColor(
                        LocalContext.current,
                        if (earthquakeDetail.magnitude.isNotEmpty()) {
                            earthquakeDetail.magnitude.toDouble()
                        } else {
                            1.0
                        }
                    )
                ),
                style = MaterialTheme.typography.displayLargeEmphasized,
            )
            IconButton(
                onClick = {
                    openWebPage(url, context)
                }
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.open_in_new_24px),
                    contentDescription = null
                )
            }
        }
        Text(
            text = earthquakeDetail.place,
            style = MaterialTheme.typography.headlineSmall,
        )
        Text(
            text = "Origin time: ${earthquakeDetail.time}",
            style = MaterialTheme.typography.bodyLarge,
        )
        Spacer(modifier = modifier.height(16.dp))
        HorizontalDivider(
            thickness = 2.dp
        )
        Spacer(modifier = modifier.height(16.dp))
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.explore_24px),
                    contentDescription = null
                )
                Text(
                    text = "Location",
                    fontSize = 16.sp,
                    style = MaterialTheme.typography.bodyLargeEmphasized,
                )
            }
            Text(
                text = formatPointCoordinates(
                    earthquakeDetail.latitude,
                    earthquakeDetail.longitude
                ),
                style = MaterialTheme.typography.bodyLargeEmphasized,
            )
        }
        Spacer(modifier = modifier.height(16.dp))
        HorizontalDivider(
            thickness = 2.dp
        )
        Spacer(modifier = modifier.height(16.dp))
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.elevation_24px),
                    contentDescription = null
                )
                Text(
                    text = "Depth",
                    style = MaterialTheme.typography.bodyLargeEmphasized,
                )
            }
            Text(
                text = "${earthquakeDetail.depth}km",
                style = MaterialTheme.typography.bodyLargeEmphasized,
            )
        }
        Spacer(modifier = modifier.height(16.dp))
        HorizontalDivider(
            thickness = 2.dp
        )
        Spacer(modifier = modifier.height(16.dp))
    }

}

fun openWebPage(url: String, context: Context) {
    val webpage = url.toUri()
    val intent = Intent(Intent.ACTION_VIEW, webpage)
    try {
        context.startActivity(intent)
    } catch (e: Exception) {
        Log.e("Error opening intent", e.message!!)
    }
}

@Preview
@Composable
private fun BottomSheetContentPreview() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        //color = MaterialTheme.colorScheme.background
    ) {
        val earthquakeDetail = EarthquakeDetail(
            eventId = "1",
            magnitude = "7.2",
            place = "Off the coast of Japan",
            time = "Feb 27, 2026, 14:11 PM",
            url = "url",
            longitude = 103.4,
            latitude = 23.4,
            depth = 1.5,
            markerLocation = null
        )

        BottomSheetContent(earthquakeDetail = earthquakeDetail)
    }
}