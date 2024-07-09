package com.spherixlabs.trekscape.home.domain.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.spherixlabs.trekscape.core.utils.constants.Constants.EMPTY_STR
import com.spherixlabs.trekscape.home.domain.enums.HomeType

/**
 * Data model descriptor of an Home Bottom Navigation Item
 *
 * @param label [String] is the label of the item.
 * @param icon [ImageVector] is the icon of the item.
 * @param type [HomeType] is the navigation screen type of the item.
 * */
data class HomeBottomNavigationItem(
    val label : String = EMPTY_STR,
    val icon  : ImageVector = Icons.Filled.Home,
    val type : HomeType = HomeType.RECOMMENDATIONS,
)
