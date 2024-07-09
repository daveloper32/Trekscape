package com.spherixlabs.trekscape.home.presentation.components.dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.spherixlabs.trekscape.R
import com.spherixlabs.trekscape.core.presentation.components.TrekScapeConfirmDialog
import com.spherixlabs.trekscape.core.presentation.ui.theme.TrekScapeTheme

@Composable
fun RequestLocationPermissionDialog(
    isOpen     : Boolean,
    onDismiss  : () -> Unit,
    onYesClick : () -> Unit,
    onNoClick  : () -> Unit,
    modifier   : Modifier = Modifier
) {
    TrekScapeConfirmDialog(
        isOpen = isOpen,
        onDismiss = onDismiss,
        onYes = onYesClick,
        onNo = onNoClick,
        title = stringResource(id = R.string.lab_required_permission),
        content = stringResource(id = R.string.lab_app_needs_permission_to_access_location),
        modifier = modifier,
    )
}

@Preview(
    showBackground = true,
    showSystemUi = true,
)
@Composable
private fun RequestPostNotificationsPermissionDialogPreview() {
    TrekScapeTheme {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            RequestLocationPermissionDialog(
                isOpen = true,
                onDismiss = { },
                onYesClick = { },
                onNoClick = { }
            )
        }
    }
}