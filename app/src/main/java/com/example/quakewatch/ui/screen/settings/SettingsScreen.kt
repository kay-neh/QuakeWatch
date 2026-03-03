package com.example.quakewatch.ui.screen.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.quakewatch.R
import com.example.quakewatch.data.source.local.datastore.SortType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    viewModel: SettingsViewModel = hiltViewModel(),
    onEvent: (SettingsEvent) -> Unit = viewModel::onEvent,
    onNavigate: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        // contentWindowInsets = WindowInsets.statusBars,
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Settings")
                },
                navigationIcon = {
                    IconButton(onClick = { onNavigate() }) {
                        Icon(painterResource(R.drawable.arrow_back_24px), contentDescription = null)
                    }
                }
            )
        }
    ) {
        if (state.isUpdatingSortType) {
            UpdateSortTypeDialog(
                state = state,
                onEvent = onEvent
            )
        }
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(it)
                .background(MaterialTheme.colorScheme.background)
        ) {
            SortTypePreference(
                state = state,
                onEvent = onEvent
            )
            ThemeModePreference()
        }
    }

}

@Composable
fun SortTypePreference(
    modifier: Modifier = Modifier,
    state: SettingsUIState,
    onEvent: (SettingsEvent) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable {
                onEvent(SettingsEvent.ShowSortTypeDialog)
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.flex_direction_24px),
            contentDescription = null
        )
        Spacer(modifier = modifier.padding(16.dp))
        Column(
            modifier = modifier
        ) {
            Text(
                text = "Sort By",
                fontSize = 18.sp,
                style = MaterialTheme.typography.labelLarge
            )
            Text(
                text = when (state.userPreference.sortType) {
                    SortType.BY_TIME -> "Time"
                    SortType.BY_MAGNITUDE -> "Magnitude"
                }
            )
        }
    }
}

@Composable
fun ThemeModePreference(
    modifier: Modifier = Modifier
) {
    var isChecked by remember { mutableStateOf(false) }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            modifier = modifier,
            imageVector = ImageVector.vectorResource(R.drawable.light_mode_24px),
            contentDescription = null
        )
        Spacer(modifier = modifier.padding(16.dp))
        Column(
            modifier = modifier
        ) {
            Text(
                text = "Night Mode",
                fontSize = 18.sp,
                style = MaterialTheme.typography.labelLarge
            )
            Text(text = if (isChecked) "On" else "Off")
        }
        Spacer(
            modifier = modifier
                .padding(16.dp)
                .weight(1f)
        )
        Switch(
            modifier = modifier,
            checked = isChecked,
            onCheckedChange = {
                isChecked = it
            },
            thumbContent = {
                Icon(
                    imageVector = if (isChecked) ImageVector.vectorResource(R.drawable.check_24px)
                    else ImageVector.vectorResource(R.drawable.close_24px),
                    contentDescription = null
                )
            }

        )
    }
}

@Composable
fun UpdateSortTypeDialog(
    modifier: Modifier = Modifier,
    state: SettingsUIState,
    onEvent: (SettingsEvent) -> Unit
) {
    var selectedSortType by remember { mutableStateOf(state.userPreference.sortType) }
    AlertDialog(
        modifier = modifier,
        onDismissRequest = {
            onEvent(SettingsEvent.HideSortTypeDialog)
            selectedSortType = state.userPreference.sortType
        },
        title = {
            Text(text = "Sort By")
        },
        text = {
            Column(
            ) {
                SortType.entries.forEach { sortType ->
                    Row(
                        modifier = modifier
                            .fillMaxWidth()
                            .clickable {
                                selectedSortType = sortType
                            },
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        RadioButton(
                            selected = selectedSortType == sortType,
                            onClick = {
                                selectedSortType = sortType
                            }
                        )
                        Text(
                            text = when (sortType) {
                                SortType.BY_TIME -> "Time"
                                SortType.BY_MAGNITUDE -> "Magnitude"
                            },
                            fontSize = 16.sp
                        )
                    }
                }
            }
        },
        confirmButton = {
            Box() {
                TextButton(
                    onClick = {
                        onEvent(SettingsEvent.UpdateSortType(selectedSortType))
                        onEvent(SettingsEvent.HideSortTypeDialog)
                    }
                ) {
                    Text(
                        text = "Ok",
                        fontSize = 16.sp
                    )
                }
            }
        },
        dismissButton = {
            Box() {
                TextButton(
                    onClick = {
                        onEvent(SettingsEvent.HideSortTypeDialog)
                        selectedSortType = state.userPreference.sortType
                    }
                ) {
                    Text(
                        text = "Cancel",
                        fontSize = 16.sp
                    )
                }
            }
        }
    )
}

@Preview
@Composable
private fun PreviewSettingsScreen() {
    SettingsScreen(onNavigate = {})
}

@Preview
@Composable
private fun PreviewSortTypePreference() {
    UpdateSortTypeDialog(
        onEvent = {},
        state = SettingsUIState()
    )
}
