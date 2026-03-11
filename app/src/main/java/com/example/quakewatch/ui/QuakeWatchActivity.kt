package com.example.quakewatch.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.quakewatch.navigation.NavigationRoot
import com.example.quakewatch.ui.theme.QuakeWatchTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuakeWatchActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            QuakeWatchTheme {
                NavigationRoot()
            }
        }
    }
}