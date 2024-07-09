package com.spherixlabs.trekscape.home.presentation.components.bottom_bar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ListAlt
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import com.spherixlabs.trekscape.R
import com.spherixlabs.trekscape.core.presentation.ui.utils.UiText
import com.spherixlabs.trekscape.home.domain.enums.HomeType
import com.spherixlabs.trekscape.home.domain.model.HomeBottomNavigationItem

/**
 * [HomeBottomBarItems] function returns all the options for the [HomeBottomBar]
 *
 * @return [List]<[HomeBottomBarItems]>
 * */
@Composable
fun HomeBottomBarItems() : List<HomeBottomNavigationItem> {
    return listOf(
        HomeBottomNavigationItem(
            label = UiText.StringResource(resId = R.string.lab_history).asString(),
            icon  = Icons.AutoMirrored.Filled.ListAlt,
            type = HomeType.HISTORY,
        ),
        HomeBottomNavigationItem(
            label = UiText.StringResource(resId = R.string.lab_recommend).asString(),
            icon  = Icons.Default.Map,
            type = HomeType.RECOMMENDATIONS,
        ),
        HomeBottomNavigationItem(
            label = UiText.StringResource(resId = R.string.lab_profile).asString(),
            icon  = Icons.Default.Person,
            type = HomeType.PROFILE,
        ),
    )
}