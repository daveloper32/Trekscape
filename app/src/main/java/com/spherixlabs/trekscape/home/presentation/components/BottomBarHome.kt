package com.spherixlabs.trekscape.home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.NotListedLocation
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spherixlabs.trekscape.core.presentation.ui.theme.TrekScapeTheme
import com.spherixlabs.trekscape.core.utils.constants.Constants.EMPTY_STR
import com.spherixlabs.trekscape.home.domain.enums.HomeType

@Composable
fun BottomBarHome(
    enable         : Boolean = true,
    timeRemaining  : String = EMPTY_STR,
    onItemClicked  : ( HomeType) -> Unit,
) {
    Row(
        modifier = Modifier
            .padding(20.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.background)
            .padding(5.dp)
            .fillMaxWidth()

    ) {
        Box(
            modifier = Modifier
                .padding(
                    end = 12.dp,
                )
                .clickable(enabled = enable) { onItemClicked(HomeType.HISTORY) }
                .padding(
                    start = 12.dp,
                )
                .align(Alignment.CenterVertically)
                .padding(20.dp)
                .weight(1f),
        ) {
            Icon(
                Icons.Rounded.Home,
                contentDescription = "",
                modifier = Modifier
                    .size(35.dp)
                    .align(Alignment.Center),
            )
        }
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .clickable(enabled = enable) { onItemClicked(HomeType.RECOMMENDATIONS) }
                .background(if (enable) MaterialTheme.colorScheme.secondary else Color.LightGray)
                .padding(20.dp),
        ) {
            if (enable) {
                Icon(
                    Icons.AutoMirrored.Rounded.NotListedLocation,
                    contentDescription = "",
                    tint     = Color.White,
                    modifier = Modifier.size(35.dp),
                )
            } else {
                Text(
                    text  = timeRemaining,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center,
                )
            }
        }
        Box(
            modifier = Modifier
                .padding(
                    start = 12.dp,
                )
                .clickable(enabled = enable) { onItemClicked(HomeType.PROFILE) }
                .padding(
                    end = 12.dp,
                )
                .align(Alignment.CenterVertically)
                .padding(20.dp)
                .weight(1f),
        ) {
            Icon(
                Icons.Rounded.Person,
                contentDescription = "",
                modifier = Modifier
                    .size(35.dp)
                    .align(Alignment.Center),
            )
        }
    }
}
@Preview
@Composable
private fun BottomBarHomePreview() {
    TrekScapeTheme {
        BottomBarHome{}
    }
}