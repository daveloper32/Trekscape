package com.spherixlabs.trekscape.about.presentation

import com.spherixlabs.trekscape.core.presentation.ui.utils.UiText

/**
 * [AboutEvent] Describe all the events that should be triggered and consumed by the view.
 * */
sealed interface AboutEvent {
    /**
     * [GoToSomeUrl] should be called when the user wants to go to some url.
     * */
    data class GoToSomeUrl(
        val url : String
    ) : AboutEvent
    /**
     * [Error] should be called when an error occurs during the view process.
     *
     * @param error [UiText] The error that occurred during the view process.
     * */
    data class Error(
        val error : UiText
    ): AboutEvent
}