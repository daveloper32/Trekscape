package com.spherixlabs.trekscape.welcome.domain.model

import com.spherixlabs.trekscape.core.presentation.ui.utils.UiText

/**
 * Data class representing categories with associated preferences that the user can select.
 *
 * @property title [UiText] The title of the category.
 * @property description [UiText]  The description explaining the category.
 * @property listPreferences  List of preferences within the category.
 */
data class CategoryPreferenceModel(
    val title           : UiText,
    val description     : UiText,
    val listPreferences : List<PreferenceModel>
)