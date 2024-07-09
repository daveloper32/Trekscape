package com.spherixlabs.trekscape.home.presentation.components.bottom_bar

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spherixlabs.trekscape.core.presentation.ui.theme.TrekScapeTheme
import com.spherixlabs.trekscape.home.domain.model.HomeBottomNavigationItem

@Composable
fun HomeBottomBar(
    isVisible                : Boolean = true,
    currentIndexItemSelected : Int,
    onItemClicked            : (Int, HomeBottomNavigationItem) -> Unit,
    modifier                 : Modifier = Modifier,
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primary,
        modifier = modifier
            .padding(16.dp)
            .clip(RoundedCornerShape(20.dp))
            .height(60.dp),
    ) {
        HomeBottomBarItems().forEachIndexed { index, itemData ->
            HomeBottomBarItem(
                isSelected = index == currentIndexItemSelected,
                label      = itemData.label,
                icon       = itemData.icon,
                onClick    = { onItemClicked(index, itemData) },
            )
        }
    }
}

@Preview
@Composable
private fun HomeBottomBarPreview() {
    TrekScapeTheme {
        HomeBottomBar(currentIndexItemSelected = 0, onItemClicked = { _, _ -> })
    }
}