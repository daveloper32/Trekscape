package com.spherixlabs.trekscape.home.presentation.components.bottom_bar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.spherixlabs.trekscape.core.presentation.ui.theme.Analogous1Color
import com.spherixlabs.trekscape.core.presentation.ui.theme.PrimaryColor

@Composable
fun RowScope.HomeBottomBarItem(
    isSelected : Boolean,
    label      : String,
    icon       : ImageVector,
    onClick    : () -> Unit,
    modifier   : Modifier = Modifier
) {
    NavigationBarItem(
        modifier = modifier,
        selected = isSelected,
        onClick  = onClick,
        icon     = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = label,
                    tint = if (isSelected) {
                        Analogous1Color.DEFAULT
                    } else {
                        Color.White
                    }
                )
                if (isSelected) {
                    Text(
                        modifier = Modifier
                            .padding(top = 4.dp),
                        text = label,
                        color = if (isSelected) {
                            Analogous1Color.DEFAULT
                        } else {
                            Color.White
                        },
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                    )
                }
            }

        },
        colors = NavigationBarItemDefaults.colors(
            indicatorColor = Color.Transparent,
            selectedIconColor = PrimaryColor.DARK,
            selectedTextColor = PrimaryColor.DARK,
        )
    )
}