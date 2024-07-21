package com.spherixlabs.trekscape.home.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.spherixlabs.trekscape.R

@Composable
fun TopBarHome(
    name    : String,
    onClick : () -> Unit = {},
){
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
        Image(
            painter            = painterResource(id = R.drawable.ic_logo),
            contentDescription = "",
            modifier           = Modifier
                .padding(end = 10.dp)
                .size(40.dp)
                .clip(CircleShape))
        Column {
            Text(
                text  = stringResource(id = R.string.lab_hi),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text  = name,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}