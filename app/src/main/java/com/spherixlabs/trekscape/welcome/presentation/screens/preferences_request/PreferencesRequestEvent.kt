package com.spherixlabs.trekscape.welcome.presentation.screens.preferences_request

import com.spherixlabs.trekscape.core.presentation.ui.utils.UiText

/**
 * [PreferencesRequestEvent] Describe all the events that should be triggered and consumed by the view.
 * */
sealed interface PreferencesRequestEvent {
    /**
     * [NavigateToHome] should be called when the user has setup all their preferences and click
     * on the next button.
     * */
    data object NavigateToHome : PreferencesRequestEvent
    /**
     * [Error] should be called when an error occurs during the view process.
     *
     * @param error [UiText] The error that occurred during the view process.
     * */
    data class Error(
        val error : UiText
    ): PreferencesRequestEvent
}