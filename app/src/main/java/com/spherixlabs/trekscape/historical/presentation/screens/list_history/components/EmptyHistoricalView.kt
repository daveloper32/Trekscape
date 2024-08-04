package com.spherixlabs.trekscape.historical.presentation.screens.list_history.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ManageSearch
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.spherixlabs.trekscape.R

@Composable
fun EmptyHistoricalView(){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(bottom = 20.dp, top = 10.dp)
            .safeDrawingPadding()
    ) {
        Icon(
            Icons.AutoMirrored.Rounded.ManageSearch,
            modifier           = Modifier
                .fillMaxWidth()
                .size(150.dp),
            tint               = Color.LightGray,
            contentDescription = "icon-manage-search",)
        Text(
            text = stringResource(R.string.lab_empty_information),
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.titleLarge
        )
    }
}