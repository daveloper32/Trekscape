package com.spherixlabs.trekscape.historical.presentation.screens.detail_historical.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Map
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.spherixlabs.trekscape.core.presentation.animations.JumpingInfiniteAnimation
import com.spherixlabs.trekscape.core.presentation.animations.RotateInfiniteAnimation
import com.spherixlabs.trekscape.core.presentation.components.TrekScapeFloatingButton

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun HeaderDetailHistoricalView(
    urlImage           : String,
    missingMeters      : String,
    isFavorite         : Boolean,
    onShowInMapClicked : () -> Unit,
    onFavoriteClicked  : () -> Unit,
) {
    Box{
        GlideImage(
            model              = urlImage,
            contentDescription = urlImage,
            contentScale       = ContentScale.Crop,
            modifier           = Modifier
                .padding(bottom = 20.dp)
                .fillMaxWidth()
                .height(250.dp)
        )
        Row(
            modifier          = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            MissingMetersView(missingMeters = missingMeters)
            Spacer(modifier = Modifier.weight(1f))
            JumpingInfiniteAnimation {
                RotateInfiniteAnimation {
                    TrekScapeFloatingButton(onClick = onShowInMapClicked) {
                        Icon(
                            Icons.Rounded.Map,
                            tint = Color.White,
                            contentDescription = "Icons.Rounded.Route",
                            modifier = Modifier.size(26.dp)
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(
                brush = Brush.verticalGradient(
                    endY = (100.dp).value,
                    colors = listOf(MaterialTheme.colorScheme.background, Color.Transparent)
                )
            )
        )
        Row(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.weight(1f))
            FavoriteButtonView(
                isFavorite = isFavorite,
                onClick = onFavoriteClicked,
            )
        }
    }
}