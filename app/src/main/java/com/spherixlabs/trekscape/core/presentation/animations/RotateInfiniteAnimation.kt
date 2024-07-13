package com.spherixlabs.trekscape.core.presentation.animations

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate

@Composable
fun RotateInfiniteAnimation(content : @Composable () -> Unit) {
    var rotationState by remember { mutableFloatStateOf(0f) }

    val rotationAnimation by rememberInfiniteTransition(label = "").animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 4500
                0f at 0 with LinearOutSlowInEasing
                360f at 3000
                360f at 4500
            },
            repeatMode = RepeatMode.Restart
        ), label = ""
    )

    rotationState = rotationAnimation
    Box(modifier = Modifier.rotate(rotationState)) {
        content()
    }

}