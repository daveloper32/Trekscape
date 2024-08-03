@file:OptIn(ExperimentalGlideComposeApi::class)

package com.spherixlabs.trekscape.about.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Link
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.spherixlabs.trekscape.R
import com.spherixlabs.trekscape.about.domain.model.DeveloperProfile

@Composable
fun ItemDeveloperProfile(
    developerProfile : DeveloperProfile,
    onClick          : () -> Unit,
    modifier         : Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(
                vertical = 8.dp,
                horizontal = 4.dp,
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        GlideImage(
            model              = developerProfile.avatarUrl,
            contentDescription = developerProfile.userName,
            contentScale       = ContentScale.Crop,
            modifier           = Modifier
                .size(70.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text  = developerProfile.name,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.SemiBold,
            )
            Text(
                text  = developerProfile.userName,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontStyle = FontStyle.Italic,
                ),
                color = MaterialTheme.colorScheme.onSurface,
            )
            Text(
                text  = developerProfile.bio,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            imageVector = Icons.Rounded.Link,
            contentDescription = stringResource(id = R.string.lab_go_to_profile),
            tint = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .padding(8.dp)
        )
    }
}