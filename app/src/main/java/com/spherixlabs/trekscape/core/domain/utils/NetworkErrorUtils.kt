package com.spherixlabs.trekscape.core.domain.utils

import com.spherixlabs.trekscape.R
import com.spherixlabs.trekscape.core.domain.utils.results.DataError
import com.spherixlabs.trekscape.core.presentation.ui.utils.UiText

/**
 * Convert a [DataError.Network] to [UiText]
 * */
fun DataError.Network.toUiText(): UiText {
    return when (this) {
        DataError.Network.NOT_INTERNET -> {
            UiText.StringResource(R.string.error_no_internet_connection)
        }
        DataError.Network.NOT_FOUND -> {
            UiText.StringResource(R.string.error_data_not_found)
        }
        DataError.Network.REQUEST_TIMEOUT -> {
            UiText.StringResource(R.string.error_timeout)
        }
        DataError.Network.TOO_MANY_REQUESTS -> {
            UiText.StringResource(R.string.error_too_many_requests)
        }
        else -> {
            UiText.StringResource(R.string.error_unknown)
        }
    }
}