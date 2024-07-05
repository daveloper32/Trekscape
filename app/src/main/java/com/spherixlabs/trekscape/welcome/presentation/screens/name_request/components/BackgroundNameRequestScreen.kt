package com.spherixlabs.trekscape.welcome.presentation.screens.name_request.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.spherixlabs.trekscape.welcome.presentation.components.CircleView

@Composable
fun BackgroundNameRequestScreen(modifier : Modifier = Modifier){
    val height : Dp= LocalConfiguration.current.screenHeightDp.dp
    Box(
        modifier = modifier
            .height(height)
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
    ) {
        CircleView(
            radius   = 250.dp,
            colorCircle  = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f),
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 150.dp, end = 100.dp)
        )
        CircleView(
            radius   = 200.dp,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(bottom = 50.dp)
        )
    }
}