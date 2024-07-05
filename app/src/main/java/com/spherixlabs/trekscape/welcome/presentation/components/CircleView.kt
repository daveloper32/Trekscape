package com.spherixlabs.trekscape.welcome.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CircleView(
    modifier    : Modifier,
    colorCircle : Color = MaterialTheme.colorScheme.primary,
    radius      : Dp    = 100.dp
){
    Canvas(modifier = modifier){
        drawCircle(
            color  = colorCircle,
            radius = radius.toPx(),
            center = center
        )
    }
}