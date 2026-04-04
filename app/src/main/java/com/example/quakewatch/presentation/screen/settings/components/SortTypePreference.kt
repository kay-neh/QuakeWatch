package com.example.quakewatch.presentation.screen.settings.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quakewatch.R
import com.example.quakewatch.domain.model.SortType
import com.example.quakewatch.presentation.screen.settings.SettingsEvent
import com.example.quakewatch.presentation.screen.settings.SettingsUIState

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
fun SortTypeDialog(
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
private fun PreviewThemeModePreference() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.background
    ) {
        SortTypePreference(
            state = SettingsUIState(),
            onEvent = {}
        )
    }
}

@Preview
@Composable
private fun PreviewSortTypeDialog() {
    SortTypeDialog(
        onEvent = {},
        state = SettingsUIState()
    )
}