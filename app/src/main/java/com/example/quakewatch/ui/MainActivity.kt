package com.example.quakewatch.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.example.quakewatch.data.QuakeWatchRepositoryImpl
import com.example.quakewatch.data.local.QuakeWatchDatabase
import com.example.quakewatch.data.local.QuakeWatchLocalDataSource
import com.example.quakewatch.data.remote.QuakeWatchRemoteDataSource
import com.example.quakewatch.data.remote.QuakeWatchService
import com.example.quakewatch.domain.QuakeWatchRepository
import com.example.quakewatch.ui.theme.QuakeWatchTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
//
    @Inject
    lateinit var x: QuakeWatchService
    @Inject
    lateinit var y: QuakeWatchDatabase

//    @Inject
//    lateinit var repository: QuakeWatchRepository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val repo = QuakeWatchRepositoryImpl(
            QuakeWatchRemoteDataSource(x),
            localDataSource = QuakeWatchLocalDataSource(y)
        )

        lifecycleScope.launch {
            repo.upsertEarthquake()
        }

        setContent {
            QuakeWatchTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                }
            }
        }
    }
}