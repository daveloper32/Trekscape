package com.spherixlabs.trekscape.historical.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.spherixlabs.trekscape.historical.domain.model.HistoricalModel

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ItemHistoricalView(historicalModel: HistoricalModel, corners : Dp = 20.dp) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp)
            .height(200.dp)
    ) {
        GlideImage(
            model              = historicalModel.urlImage,
            contentDescription = historicalModel.urlImage,
            contentScale       = ContentScale.Crop,
            modifier           = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(corners))

        )
        Row(modifier = Modifier
            .align(alignment = Alignment.BottomCenter)
            .clip(RoundedCornerShape(corners))
            .fillMaxWidth()
            .background(brush = Brush.verticalGradient(listOf(Color.Transparent, Color.Black)))
            .padding(15.dp)
        ) {
            Icon(Icons.Rounded.LocationOn, contentDescription = "", tint = Color.White)
            Text(
                text     = historicalModel.name,
                style    = MaterialTheme.typography.bodyLarge,
                color    = Color.White,
                modifier = Modifier.padding(start = 10.dp)
            )
        }
    }
}