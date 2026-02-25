package com.example.quakewatch.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.example.quakewatch.ui.screen.earthquakeDetail.EarthquakeDetailScreen
import com.example.quakewatch.ui.screen.earthquakeList.EarthquakeListScreen
import com.example.quakewatch.ui.screen.settings.SettingsScreen

@Composable
fun NavigationRoot(modifier: Modifier = Modifier) {

    val backStack = rememberNavBackStack(
        elements = arrayOf(Route.EarthquakeList)
    )

    NavDisplay(
        modifier = modifier,
        backStack = backStack,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = entryProvider {
            entry<Route.EarthquakeList> {
                EarthquakeListScreen()
            }
            entry<Route.EarthquakeDetail> {
                EarthquakeDetailScreen()
            }
            entry<Route.Settings> {
                SettingsScreen()
            }
        }
    )
}