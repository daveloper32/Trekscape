package com.spherixlabs.trekscape.core.presentation.animations

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector4D
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color

@Composable
fun ColorTransitionAnimation(
    changeColor    : Boolean,
    colorAnimatable: Animatable<Color, AnimationVector4D>,
    baseColor      : Color,
    finalColor     : Color,
    duration       : Int = 1000
){
    LaunchedEffect(changeColor) {
        if(changeColor){
            colorAnimatable.animateTo(
                finalColor,
                animationSpec = tween(durationMillis = duration, easing = LinearEasing)
            )
        } else {
            colorAnimatable.animateTo(
                baseColor,
                animationSpec = tween(durationMillis = duration, easing = LinearEasing)
            )
        }
    }
}