package com.example.quakewatch.presentation.screen.earthquakeDetail.components

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.quakewatch.R
import com.example.quakewatch.presentation.screen.earthquakeDetail.EarthquakeDetail
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import com.google.maps.android.compose.rememberUpdatedMarkerState

@Composable
fun MapView(
    modifier: Modifier = Modifier,
    earthquakeDetail: EarthquakeDetail
) {

    Log.e("MapView", "earthquakePosition: ${earthquakeDetail.markerLocation}")
    val context = LocalContext.current

    // 1. Check if the system is in Dark Mode
    val isDarkMode = isSystemInDarkTheme()

    // 2. Remember the MapProperties and update them when the theme changes
    val mapProperties = remember(isDarkMode) {
        MapProperties(
            mapStyleOptions = if (isDarkMode) {
                MapStyleOptions.loadRawResourceStyle(context, R.raw.map_style_dark)
            } else {
                null // null reverts to the default light system theme
            }
        )
    }

    // Initialize the camera position state, which controls the camera's position on the map
    val cameraPositionState = rememberCameraPositionState()

    LaunchedEffect(earthquakeDetail.markerLocation) {
        earthquakeDetail.markerLocation?.let { target ->
            // This block runs ONLY when the Composable is "Active",
            // meaning the SDK is initialized.
            try {
                cameraPositionState.animate(
                    update = CameraUpdateFactory.newLatLngZoom(target, 7.5f)
                )
            } catch (e: Exception) {
                Log.e("MapsError", "Factory not ready yet", e)
            }
        }
    }

    // Display the Google Map without
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        properties = mapProperties,
        uiSettings = MapUiSettings(zoomControlsEnabled = false),
        cameraPositionState = cameraPositionState
    ) {
        earthquakeDetail.markerLocation?.let {
            Marker(
                state = rememberUpdatedMarkerState(position = it),
                title = earthquakeDetail.place,
                snippet = "Marker in Location"
            )
        }
    }
}

@Preview
@Composable
private fun PreviewMap() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        //color = MaterialTheme.colorScheme.background
    ) {

    }
}