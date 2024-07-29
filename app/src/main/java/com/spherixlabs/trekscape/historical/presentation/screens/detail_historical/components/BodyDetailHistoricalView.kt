package com.spherixlabs.trekscape.historical.presentation.screens.detail_historical.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.spherixlabs.trekscape.R
import com.spherixlabs.trekscape.core.presentation.components.TrekScapeActionButton
import com.spherixlabs.trekscape.core.presentation.components.TrekScapeWritingTextAnimation

@Composable
fun BodyDetailHistoricalView(
    name            : String,
    description     : String,
    onDeleteClicked : () -> Unit,
) {
    Column(modifier = Modifier.padding(20.dp)) {
        TrekScapeWritingTextAnimation(
            text       = name,
            textStyle  = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold),
            textColor  = MaterialTheme.colorScheme.onSurface,
            animationDuration = 500,
            delayBeforeStart  = 200,
        )
        Text(
            text  = description,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
        )
        TrekScapeActionButton(
            text      = stringResource(id = R.string.lab_delete),
            isLoading = false,
            enabled   = true,
            modifier  = Modifier.padding(vertical = 40.dp) ,
            onClick   = onDeleteClicked,
        )
    }
}