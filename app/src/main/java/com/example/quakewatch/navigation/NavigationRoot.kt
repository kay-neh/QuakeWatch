package com.example.quakewatch.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.example.quakewatch.ui.screen.earthquakeDetail.EarthquakeDetailScreen
import com.example.quakewatch.ui.screen.earthquakeFeed.EarthquakeFeedScreen
import com.example.quakewatch.ui.screen.settings.SettingsScreen

@Composable
fun NavigationRoot(modifier: Modifier = Modifier) {

    val backStack = rememberNavBackStack(
        elements = arrayOf(Route.EarthquakeFeed)
    )
    NavDisplay(
        modifier = modifier.fillMaxSize(),
        backStack = backStack,
        transitionSpec = {
            slideInHorizontally { it } + fadeIn() togetherWith
                    slideOutHorizontally { -it } + fadeOut()
        },
        popTransitionSpec = {
            slideInHorizontally { -it } + fadeIn() togetherWith
                    slideOutHorizontally { it } + fadeOut()
        },
        predictivePopTransitionSpec = {
            slideInHorizontally { -it } + fadeIn() togetherWith
                    slideOutHorizontally { it } + fadeOut()
        },
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = entryProvider {
            entry<Route.EarthquakeFeed> {
                EarthquakeFeedScreen(
                    onNavigate = { backStack.add(Route.Settings) },
                    onItemClick = { backStack.add(Route.EarthquakeDetail(it)) }
                )
            }
            entry<Route.EarthquakeDetail> {
                EarthquakeDetailScreen(eventId = it.eventId)
            }
            entry<Route.Settings> {
                SettingsScreen(
                    onNavigate = { backStack.remove(Route.Settings) }
                )
            }
        }
    )

}