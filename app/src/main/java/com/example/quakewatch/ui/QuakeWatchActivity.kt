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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.quakewatch.data.DefaultQuakeWatchRepository
import com.example.quakewatch.data.source.local.EarthquakeDao
import com.example.quakewatch.data.source.local.QuakeWatchDatabase
import com.example.quakewatch.data.source.local.EarthquakeLocalDataSource
import com.example.quakewatch.data.source.local.LocalDataSource
import com.example.quakewatch.data.source.network.EarthquakeNetworkDataSource
import com.example.quakewatch.data.source.network.NetworkDataSource
import com.example.quakewatch.data.source.network.QuakeWatchService
import com.example.quakewatch.domain.QuakeWatchRepository
import com.example.quakewatch.ui.screens.earthquakes.EarthquakesViewModel
import com.example.quakewatch.ui.theme.QuakeWatchTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class QuakeWatchActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val earthquakesViewModel by viewModels<EarthquakesViewModel>()
        earthquakesViewModel.deleteAll()

        setContent {
            QuakeWatchTheme {
                //val earthquakeViewModel: EarthquakesViewModel = viewModel()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                }
            }
        }
    }
}