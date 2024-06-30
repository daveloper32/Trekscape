package com.spherixlabs.trekscape.welcome.presentation.screens.name_request

import com.spherixlabs.trekscape.core.presentation.ui.utils.UiText

/**
 * [NameRequestEvent] Describe all the events that should be triggered and consumed by the view.
 * */
sealed interface NameRequestEvent {
    /**
     * [NavigateToSetupPreferences] should be called when the user has entered their name and click
     * on the next button.
     * */
    data object NavigateToSetupPreferences : NameRequestEvent
    /**
     * [Error] should be called when an error occurs during the view process.
     *
     * @param error [UiText] The error that occurred during the view process.
     * */
    data class Error(
        val error : UiText
    ): NameRequestEvent
}