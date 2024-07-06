package com.spherixlabs.trekscape.welcome.presentation.screens.preferences_request

/**
 * [PreferencesRequestAction] Describe all the actions that can happen in the view.
 * */
sealed interface PreferencesRequestAction {
    /**
     * [OnNextCategoryPreference] trigger when the user wants to move to the next category of preferences.
     *
     * */
    data object OnNextCategoryPreference     : PreferencesRequestAction
    /**
     * [OnPreviousCategoryPreference]trigger  when the user wants to return to the previous category of preferences.
     *
     * */
    data object OnPreviousCategoryPreference : PreferencesRequestAction
    /**
     * Data class representing the action triggered when the user selects or deselects a preference.
     *
     * @property preference The identifier of the preference being selected or deselected.
     */
    data class OnSelectOrDeselectPreference(val preference : String) : PreferencesRequestAction
}