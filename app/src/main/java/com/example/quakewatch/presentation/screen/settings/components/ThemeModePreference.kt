package com.example.quakewatch.presentation.screen.settings.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
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
import com.example.quakewatch.presentation.screen.earthquakeFeed.components.EarthquakeItemPreview
import com.example.quakewatch.presentation.screen.settings.SettingsScreen
import com.example.quakewatch.presentation.screen.settings.SettingsUIState

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

@Preview
@Composable
private fun PreviewThemeModePreference() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.background
    ) {
        ThemeModePreference()
    }
}