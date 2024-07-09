package com.spherixlabs.trekscape.welcome.presentation.screens.preferences_request

import com.spherixlabs.trekscape.welcome.domain.model.CategoryPreferenceModel
import com.spherixlabs.trekscape.welcome.domain.model.PreferenceModel


/**
 * Describe the state [PreferencesRequestState] of the name request screen.
 *
 * @property categories List of [CategoryPreferenceModel] representing different preference categories.
 * @property currentCategory [Int] The index of the current preference category being displayed.
 * @property preferencesSelected List of [PreferenceModel] selected by the user.
 * @property isShowingLastCategory [Boolean] Indicates if the last preference category is currently displayed.
 * @property showButtonBack [Boolean] Indicates whether the back button should be shown on the screen.
 * @property showNext [Boolean] Indicates whether the next button should be shown on the screen.
 * */
data class PreferencesRequestState(
    val categories            : List<CategoryPreferenceModel> = emptyList(),
    val currentCategory       : Int = 0,
    val preferencesSelected   : List<PreferenceModel> = listOf(),
    val isShowingLastCategory : Boolean = false,
    val showButtonBack        : Boolean = false,
    val showNext              : Boolean = false,
)