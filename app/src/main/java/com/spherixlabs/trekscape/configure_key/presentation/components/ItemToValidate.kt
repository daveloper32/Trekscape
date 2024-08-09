package com.spherixlabs.trekscape.configure_key.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.CircularProgressIndicator
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
fun ItemToValidate(
    text      : String,
    isLoading : Boolean = false,
    isValid   : Boolean = false,
    modifier  : Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier          = modifier
            .safeDrawingPadding()
            .padding(horizontal = 15.dp, vertical = 5.dp)
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(20.dp),
                color = MaterialTheme.colorScheme.primary,
            )
            Spacer(modifier = Modifier.width(4.dp))
        }
        if (!isLoading) {
            Icon(
                imageVector = if (isValid) Icons.Rounded.Check else Icons.Rounded.Close,
                contentDescription = stringResource(id = R.string.lab_is_valid),
                tint = if (isValid) Color.Green else Color.Red,
            )
        }
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text  = text,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
}