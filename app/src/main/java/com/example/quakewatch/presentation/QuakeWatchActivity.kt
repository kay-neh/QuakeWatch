package com.example.quakewatch.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.quakewatch.navigation.NavigationRoot
import com.example.quakewatch.ui.theme.QuakeWatchTheme
import com.google.android.gms.maps.MapsInitializer
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuakeWatchActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Force initialization
        MapsInitializer.initialize(applicationContext, MapsInitializer.Renderer.LATEST) {
            // SDK is initialized
        }

        setContent {
            QuakeWatchTheme {
                NavigationRoot()
            }
        }
    }
}