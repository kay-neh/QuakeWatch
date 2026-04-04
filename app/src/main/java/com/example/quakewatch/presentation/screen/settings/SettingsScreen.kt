package com.example.quakewatch.presentation.screen.settings

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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.quakewatch.R
import com.example.quakewatch.domain.model.SortType
import com.example.quakewatch.presentation.screen.settings.components.SortTypeDialog
import com.example.quakewatch.presentation.screen.settings.components.SortTypePreference
import com.example.quakewatch.presentation.screen.settings.components.ThemeModePreference

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    viewModel: SettingsViewModel,
    onEvent: (SettingsEvent) -> Unit = viewModel::onEvent,
    onNavigate: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    SettingsScreen(
        modifier = modifier,
        state = state,
        onEvent = onEvent,
        onNavigate = onNavigate
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    state: SettingsUIState,
    onEvent: (SettingsEvent) -> Unit,
    onNavigate: () -> Unit
) {

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
            SortTypeDialog(
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

@Preview
@Composable
private fun PreviewSettingsScreen() {
    SettingsScreen(
        state = SettingsUIState(),
        onEvent = {},
        onNavigate = {}
    )
}
