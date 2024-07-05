package com.spherixlabs.trekscape.core.presentation.components

import androidx.compose.animation.Animatable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.spherixlabs.trekscape.core.presentation.Animations.ColorTransitionAnimation

@Composable
fun TrekScapeFloatingButton(
    modifier       : Modifier = Modifier,
    onClick        : () -> Unit,
    enable         : Boolean = true,
    enableColor    : Color  = Color.LightGray,
    containerColor : Color = MaterialTheme.colorScheme.secondary,
    content        : @Composable () -> Unit,
){
    val colorAnimatable = remember { Animatable(enableColor) }
    ColorTransitionAnimation(
        changeColor     = enable,
        colorAnimatable = colorAnimatable,
        baseColor       = enableColor,
        finalColor      = containerColor,
        duration        = 250
    )
    FloatingActionButton(
        onClick        = if(enable) onClick else {{}},
        containerColor = colorAnimatable.value,
        content        = content,
        modifier       = modifier
    )
}

@Preview
@Composable
private fun TrekScapeFloatingButtonPreview(){
    TrekScapeFloatingButton(
        content = { Icon(Icons.Filled.Add, contentDescription = "") },
        onClick = {}
    )
}