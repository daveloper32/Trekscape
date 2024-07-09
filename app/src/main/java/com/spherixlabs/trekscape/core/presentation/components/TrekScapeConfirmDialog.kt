package com.spherixlabs.trekscape.core.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spherixlabs.trekscape.R
import com.spherixlabs.trekscape.core.presentation.ui.theme.TrekScapeTheme
import com.spherixlabs.trekscape.core.utils.constants.Constants.EMPTY_STR

@Composable
fun TrekScapeConfirmDialog(
    isOpen    : Boolean,
    onDismiss : () -> Unit,
    onYes     : () -> Unit,
    onNo      : () -> Unit,
    title     : String = EMPTY_STR,
    content   : String = EMPTY_STR,
    yesText   : String = stringResource(id = R.string.lab_yes),
    noText    : String = stringResource(id = R.string.lab_no),
    icon      : ImageVector? = null,
    modifier  : Modifier = Modifier,
) {
    TrekScapeDialog(
        isOpen = isOpen,
        onDismiss = onDismiss,
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                if (icon != null) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                    Spacer(
                        modifier = Modifier
                            .width(8.dp)
                    )
                }
                Text(
                    text  = title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center,
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text  = content,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                TrekScapeActionButton(
                    text = noText,
                    isLoading = false,
                    enabled = true,
                    onClick = onNo,
                    modifier = Modifier
                        .weight(1f),
                )
                Spacer(
                    modifier = Modifier
                        .width(12.dp)
                )
                TrekScapeActionButton(
                    text = yesText,
                    isLoading = false,
                    enabled = true,
                    onClick = onYes,
                    modifier = Modifier
                        .weight(1f),
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
)
@Composable
private fun Smart1ConfirmDialogPreview() {
    TrekScapeTheme {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TrekScapeConfirmDialog(
                isOpen = true,
                onDismiss = { },
                onYes = { },
                onNo = { },
                title = "Title",
                content = "Content content content",
                icon = Icons.Default.Notifications,
            )
        }
    }
}