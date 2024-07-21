package com.spherixlabs.trekscape.profile.presentation

import com.spherixlabs.trekscape.core.presentation.ui.utils.UiText

/**
 * [ProfileEvent] Describe all the events that should be triggered and consumed by the view.
 * */
sealed interface ProfileEvent {
    /**
     * [GoToGeneralPreferences] should be called when the user wants to go to the general preferences
     * */
    data object GoToGeneralPreferences : ProfileEvent
    /**
     * [GoToLocationPreferences] should be called when the user wants to go to the location preferences
     * */
    data object GoToLocationPreferences : ProfileEvent
    /**
     * [Error] should be called when an error occurs during the view process.
     *
     * @param error [UiText] The error that occurred during the view process.
     * */
    data class Error(
        val error : UiText
    ): ProfileEvent
}