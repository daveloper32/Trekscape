package com.spherixlabs.trekscape.core.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.spherixlabs.trekscape.R

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun TrekScapeMagicLoadingDialog(
    isOpen    : Boolean,
    onDismiss : () -> Unit,
    modifier  : Modifier = Modifier,
) {
    if (isOpen) {
        Dialog(
            onDismissRequest = onDismiss,
        ) {
            Column(
                modifier = modifier,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                GlideImage(
                    model              = R.drawable.ic_magic_wand,
                    contentDescription = stringResource(id = R.string.lab_magic_wand),
                    contentScale       = ContentScale.Crop,
                    modifier           = Modifier
                        .padding(bottom = 20.dp)
                        .clip(RectangleShape)
                        .fillMaxWidth()
                        .height(320.dp),
                )
            }
        }
    }
}