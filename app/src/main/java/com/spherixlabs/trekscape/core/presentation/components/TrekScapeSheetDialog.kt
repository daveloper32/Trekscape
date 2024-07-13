package com.spherixlabs.trekscape.core.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.spherixlabs.trekscape.R
import com.spherixlabs.trekscape.core.presentation.animations.JumpingInfiniteAnimation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrekScapeSheetDialog(
    isOpen    : Boolean,
    showLabel : Boolean = true,
    expanded  : Boolean = false,
    onDismiss : () -> Unit,
    modifier  : Modifier = Modifier,
    content   : @Composable () -> Unit,
){
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = expanded)
    if (isOpen) {
        Box {
            ModalBottomSheet(
                sheetState       = bottomSheetState,
                windowInsets     = WindowInsets.statusBars,
                containerColor   = MaterialTheme.colorScheme.background,
                modifier         = modifier,
                onDismissRequest = onDismiss) {
                Column(horizontalAlignment = Alignment.CenterHorizontally){
                    if(showLabel){
                        JumpingInfiniteAnimation {
                            Text(
                                text     = stringResource(id = R.string.lab_swipe_up),
                                style    = MaterialTheme.typography.bodySmall,
                                color    =  MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.padding(bottom = 5.dp)
                            )
                        }
                    }
                    content()
                }
            }
        }
    }
}