package com.spherixlabs.trekscape.welcome.presentation.screens.preferences_request


/**
 * Describe the state [PreferencesRequestState] of the name request screen.
 *
 * @property currentCategory [Int] The index of the current preference category being displayed.
 * @property preferencesSelected List of identifiers for preferences that have been selected.
 * @property isShowingLastCategory [Boolean] Indicates if the last preference category is currently displayed.
 * @property showButtonBack [Boolean] Indicates whether the back button should be shown on the screen.
 * */
data class PreferencesRequestState(
    val currentCategory       : Int = 0,
    val preferencesSelected   : List<String> = listOf(),
    val isShowingLastCategory : Boolean = false,
    val showButtonBack        : Boolean = false
)