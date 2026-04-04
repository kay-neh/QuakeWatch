package com.example.quakewatch.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.example.quakewatch.presentation.screen.earthquakeDetail.EarthquakeDetailScreen
import com.example.quakewatch.presentation.screen.earthquakeDetail.EarthquakeDetailViewModel
import com.example.quakewatch.presentation.screen.earthquakeFeed.EarthquakeFeedScreen
import com.example.quakewatch.presentation.screen.earthquakeFeed.EarthquakeFeedViewModel
import com.example.quakewatch.presentation.screen.settings.SettingsScreen
import com.example.quakewatch.presentation.screen.settings.SettingsViewModel

@Composable
fun NavigationRoot(modifier: Modifier = Modifier) {

    val backStack = rememberNavBackStack(
        Route.EarthquakeFeed
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
                val viewModel: EarthquakeFeedViewModel = hiltViewModel()
                EarthquakeFeedScreen(
                    viewModel = viewModel,
                    onNavigate = { backStack.add(Route.Settings) },
                    onItemClick = {
                        backStack.add(Route.EarthquakeDetail(it))
                    }
                )
            }
            entry<Route.EarthquakeDetail> {
                val viewmodel: EarthquakeDetailViewModel =
                    hiltViewModel<EarthquakeDetailViewModel, EarthquakeDetailViewModel.Factory>(
                        creationCallback = { factory ->
                            factory.create(
                                eventId = it.eventId
                            )
                        }
                    )
                EarthquakeDetailScreen(
                    viewModel = viewmodel,
                    onNavigate = { eventId ->
                        backStack.remove(Route.EarthquakeDetail(eventId = eventId))
                    }
                )
            }
            entry<Route.Settings> {
                val viewModel: SettingsViewModel = hiltViewModel()
                SettingsScreen(
                    viewModel = viewModel,
                    onNavigate = { backStack.remove(Route.Settings) }
                )
            }
        }
    )

}