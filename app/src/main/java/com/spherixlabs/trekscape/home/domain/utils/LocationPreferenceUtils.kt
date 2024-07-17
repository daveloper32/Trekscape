package com.spherixlabs.trekscape.home.domain.utils

import com.spherixlabs.trekscape.R
import com.spherixlabs.trekscape.core.presentation.ui.utils.UiText
import com.spherixlabs.trekscape.home.domain.enums.LocationPreference

/**
 * Convert a [LocationPreference] to [UiText]
 * */
fun LocationPreference.toUiText(): UiText {
    return when (this) {
        LocationPreference.ALL_WORLD -> {
            UiText.StringResource(R.string.lab_all_world)
        }
        LocationPreference.SAME_CONTINENT -> {
            UiText.StringResource(R.string.lab_same_continent)
        }
        LocationPreference.SAME_COUNTRY -> {
            UiText.StringResource(R.string.lab_same_country)
        }
        LocationPreference.SAME_CITY -> {
            UiText.StringResource(R.string.lab_same_city)
        }
    }
}