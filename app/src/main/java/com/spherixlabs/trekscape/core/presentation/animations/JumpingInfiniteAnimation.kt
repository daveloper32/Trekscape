package com.spherixlabs.trekscape.core.presentation.animations

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity

@Composable
fun JumpingInfiniteAnimation(
    initialValue: Float = 0f,
    targetValue: Float  = -30f,
    content : @Composable () -> Unit,
){
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val offsetY by infiniteTransition.animateFloat(
        label         = "",
        initialValue  = initialValue,
        targetValue   = targetValue,
        animationSpec = infiniteRepeatable(
                animation  = tween( durationMillis = 500,easing =  LinearOutSlowInEasing),
                repeatMode = RepeatMode.Reverse
        )
    )
    Box(modifier = Modifier.offset(y = with(LocalDensity.current){ offsetY.toDp() })){
        content()
    }

}