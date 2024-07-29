package com.spherixlabs.trekscape.historical.presentation.screens.detail_historical.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.spherixlabs.trekscape.R
import com.spherixlabs.trekscape.core.presentation.ui.theme.Analogous1Color

@Composable
fun FavoriteButtonView(
    isFavorite : Boolean,
    onClick    : () -> Unit,
    modifier   : Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .clickable { onClick() }
            .shadow(elevation = 5.dp, shape = CircleShape)
            .background(color = Analogous1Color.C700, shape = CircleShape)
            .padding(8.dp)

    ) {
        Icon(
            imageVector = if (isFavorite) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
            contentDescription = stringResource(id = R.string.lab_is_favorite),
            tint = if (isFavorite) Color.Red else Color.White,
        )
    }
}