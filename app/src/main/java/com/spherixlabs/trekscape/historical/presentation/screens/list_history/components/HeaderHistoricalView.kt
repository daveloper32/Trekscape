package com.spherixlabs.trekscape.historical.presentation.screens.list_history.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.spherixlabs.trekscape.R

@Composable
fun HeaderHistoricalView(){
    Text(
        text     = stringResource(id = R.string.lab_history),
        style    = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
        color    = MaterialTheme.colorScheme.onSurface,
        modifier = Modifier.padding(bottom = 20.dp)
    )
}