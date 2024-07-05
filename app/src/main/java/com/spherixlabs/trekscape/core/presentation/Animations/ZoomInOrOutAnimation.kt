package com.spherixlabs.trekscape.core.presentation.Animations

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ZoomInOrOutAnimation(
    modifier: Modifier = Modifier,
    show    : Boolean,
    duration: Int = 300,
    content : @Composable () -> Unit,
) {
    AnimatedVisibility(
        modifier = modifier,
        visible  = show,
        enter    = scaleIn (animationSpec = tween(durationMillis = duration)) +
                  fadeIn   (animationSpec = tween(durationMillis = duration)),
        exit     = scaleOut(animationSpec = tween(durationMillis = duration)) +
                  fadeOut  (animationSpec = tween(durationMillis = duration)),
    ) {
        content()
    }
}