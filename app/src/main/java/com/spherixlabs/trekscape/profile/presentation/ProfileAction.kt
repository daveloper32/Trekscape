package com.spherixlabs.trekscape.profile.presentation

/**
 * [ProfileAction] Describe all the actions that can happen in the view.
 * */
sealed interface ProfileAction {
    /**
     * [OnScreenStarted] should be called when the screen is fully started.
     * */
    data object OnScreenStarted : ProfileAction
    /**
     * [OnEditGeneralPreferences] should be called when the user wants to edit the general preferences
     * */
    data object OnEditGeneralPreferences : ProfileAction
    /**
     * [OnEditLocationPreferences] should be called when the user wants to edit the location preferences
     * */
    data object OnEditLocationPreferences : ProfileAction
}