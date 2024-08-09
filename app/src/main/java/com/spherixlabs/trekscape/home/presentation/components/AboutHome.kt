package com.spherixlabs.trekscape.home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.spherixlabs.trekscape.R

@Composable
fun AboutHome(
    onClick : () -> Unit = {},
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier          = Modifier
            .safeDrawingPadding()
            .padding(10.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 15.dp, vertical = 5.dp)
            .clickable { onClick() }
    ) {
        Icon(
            imageVector = Icons.Rounded.Info,
            contentDescription = stringResource(id = R.string.lab_info),
            tint = MaterialTheme.colorScheme.onSurface,
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text  = stringResource(id = R.string.lab_about),
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}