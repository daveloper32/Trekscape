package com.spherixlabs.trekscape.historical.presentation.screens.list_history.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.spherixlabs.trekscape.R

@Composable
fun HeaderHistoricalView(showFavorites : Boolean, onSelectFavorite:(Boolean) -> Unit){
    Column {
        Text(
            text     = stringResource(id = R.string.lab_history),
            style    = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            color    = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(bottom = 20.dp)
        )
        Row {
            TagHistoricalView(
                icon     = Icons.Default.FilterList,
                title    = stringResource(id = R.string.lab_all),
                onSelect = {onSelectFavorite(false)},
                isSelect = !showFavorites)
            Spacer(modifier = Modifier.width(10.dp))
            TagHistoricalView(
                icon     = Icons.Default.Favorite,
                title    = stringResource(id = R.string.lab_favorites),
                onSelect = {onSelectFavorite(true)},
                isSelect = showFavorites)
        }
        Spacer(modifier = Modifier.height(10.dp))
    }
}
@Composable
private fun TagHistoricalView(
    icon     : ImageVector,
    title    : String,
    isSelect : Boolean,
    onSelect : () -> Unit
){
    val backgroundColor = if (isSelect) MaterialTheme.colorScheme.primary else Color.LightGray
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier         = Modifier
            .background(color = backgroundColor, shape = CircleShape)
            .clickable { onSelect()}
            .padding(8.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Spacer(modifier = Modifier.width(10.dp))
            Icon(
                icon,
                contentDescription = "preference-icon",
                modifier = Modifier.size(16.dp))
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text  = title,
                style = MaterialTheme.typography.bodyMedium,
                color = if(isSelect) Color.White else MaterialTheme.colorScheme.onSurface)
            Spacer(modifier = Modifier.width(10.dp))
        }
    }
}