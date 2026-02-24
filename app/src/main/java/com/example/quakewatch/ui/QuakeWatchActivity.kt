package com.example.quakewatch.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.example.quakewatch.ui.screen.earthquakes.EarthquakesViewModel
import com.example.quakewatch.ui.theme.QuakeWatchTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class QuakeWatchActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val earthquakesViewModel by viewModels<EarthquakesViewModel>()
        lifecycleScope.launch {
            earthquakesViewModel.earthquakes.collect { earthquakes ->
                for (earthquake in earthquakes) {
                    println(earthquake)
                }
            }
        }


        setContent {
            QuakeWatchTheme {
                //val earthquakeViewModel: EarthquakesViewModel = viewModel()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                }
            }
        }
    }
}