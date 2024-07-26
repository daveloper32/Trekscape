package com.spherixlabs.trekscape.home.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spherixlabs.trekscape.R


@Composable
fun MarkerWithImage() {
    Box(
        contentAlignment = Alignment.TopCenter,
        modifier         = Modifier.padding(16.dp)
    ) {
        Image(
            painter            = painterResource(id = R.drawable.ic_pin),
            contentDescription = "marker-background",
            modifier           = Modifier.size(50.dp))
        Image(
            painter            = painterResource(id = R.drawable.ic_magic),
            contentDescription = "marker",
            contentScale       = ContentScale.Crop,
            modifier           = Modifier
                .padding(top = 5.dp)
                .size(30.dp)
                .clip(CircleShape)
        )
    }
}

@Composable
@Preview
private fun Preview(){
    MarkerWithImage()
}