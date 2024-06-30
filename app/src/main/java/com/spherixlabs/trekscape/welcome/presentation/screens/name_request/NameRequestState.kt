package com.spherixlabs.trekscape.welcome.presentation.screens.name_request

import com.spherixlabs.trekscape.core.utils.constants.Constants.EMPTY_STR

/**
 * Describe the state [NameRequestState] of the name request screen.
 *
 * @property name [String] The user's name.
 * @property isGoingNext [Boolean] Indicates whether the next button is loading or not.
 * @property canGoNext [Boolean] Indicates whether the next button should be enabled or not.
 * */
data class NameRequestState(
    val name        : String = EMPTY_STR,
    val isGoingNext : Boolean = false,
    val canGoNext   : Boolean = false,
)
