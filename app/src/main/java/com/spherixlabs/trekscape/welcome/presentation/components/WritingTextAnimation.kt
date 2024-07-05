package com.spherixlabs.trekscape.welcome.presentation.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.spherixlabs.trekscape.core.utils.constants.Constants
import kotlinx.coroutines.delay

@Composable
fun WritingTextAnimation(
    text             : String,
    textStyle        : TextStyle = MaterialTheme.typography.displayLarge.copy(fontWeight = FontWeight.Bold),
    textColor        : Color     = MaterialTheme.colorScheme.onSurface,
    animationDuration: Int = 2000,
    delayBeforeStart : Int = 0,
){
    var textToShow     by remember { mutableStateOf(Constants.EMPTY_STR) }
    var fadeInAlpha    by remember { mutableFloatStateOf(0f) }
    val fadeInAnimation = remember { Animatable(0f) }
    val textLength      = text.length

    LaunchedEffect(key1 = true) {
        fadeInAnimation.animateTo(
            targetValue   = 1f,
            animationSpec = tween(
                    durationMillis = 1000,
                    easing         = FastOutSlowInEasing
                )
        )
    }

    fadeInAlpha = fadeInAnimation.value
    val animation = remember { Animatable(0f) }

    LaunchedEffect(key1 = text) {
        delay(delayBeforeStart.toLong())
        animation.animateTo(
            targetValue   = textLength.toFloat(),
            animationSpec = tween(
                    durationMillis = animationDuration,
                    easing         = LinearEasing
                )
        )
    }

    val currentLength = animation.value.toInt()
    textToShow        = text.substring(0, currentLength.coerceAtMost(textLength))
    Text(
        text  = textToShow,
        style = textStyle,
        color = textColor
    )
}