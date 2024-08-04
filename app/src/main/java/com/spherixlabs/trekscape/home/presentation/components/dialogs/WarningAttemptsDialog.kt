package com.spherixlabs.trekscape.home.presentation.components.dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.spherixlabs.trekscape.R
import com.spherixlabs.trekscape.core.presentation.components.TrekScapeConfirmDialog
import com.spherixlabs.trekscape.core.presentation.ui.theme.TrekScapeTheme

@Composable
fun WarningAttemptsDialog(
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
        title = stringResource(R.string.lab_warning),
        content = stringResource(R.string.lab_warning_attempts),
        modifier = modifier,
    )
}

@Preview(
    showBackground = true,
    showSystemUi = true,
)
@Composable
private fun WarningAttemptsDialogPreview() {
    TrekScapeTheme {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            WarningAttemptsDialog(
                isOpen = true,
                onDismiss = { },
                onYesClick = { },
                onNoClick = { }
            )
        }
    }
}