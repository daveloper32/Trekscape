package com.spherixlabs.trekscape.historical.presentation.screens.list_history.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.spherixlabs.trekscape.historical.presentation.screens.detail_historical.components.FavoriteButtonView
import com.spherixlabs.trekscape.place.domain.model.PlaceData

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ItemHistoricalView(
    place              : PlaceData,
    corners            : Dp = 20.dp,
    onFavoriteClicked  : () -> Unit,
    onClick            : (PlaceData) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp)
            .height(200.dp)
            .clickable { onClick(place) }
    ) {
        GlideImage(
            model              = place.imageUrl,
            contentDescription = place.name,
            contentScale       = ContentScale.Crop,
            modifier           = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(corners))

        )
        Row(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.weight(1f))
            FavoriteButtonView(
                isFavorite = place.isFavorite,
                onClick = onFavoriteClicked,
            )
        }
        Row(modifier = Modifier
            .align(alignment = Alignment.BottomCenter)
            .clip(RoundedCornerShape(corners))
            .fillMaxWidth()
            .background(brush = Brush.verticalGradient(listOf(Color.Transparent, Color.Black)))
            .padding(15.dp)
        ) {
            Icon(Icons.Rounded.LocationOn, contentDescription = "", tint = Color.White)
            Text(
                text     = place.name,
                style    = MaterialTheme.typography.bodyLarge,
                color    = Color.White,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(start = 10.dp).weight(1f)
            )
            Text(
                text     = place.missingMeters,
                style    = MaterialTheme.typography.bodyLarge,
                color    = Color.White,
                modifier = Modifier.padding(start = 10.dp)
            )
        }
    }
}