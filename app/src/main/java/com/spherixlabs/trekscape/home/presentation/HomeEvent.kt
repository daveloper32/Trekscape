package com.spherixlabs.trekscape.home.presentation

import com.spherixlabs.trekscape.core.presentation.ui.utils.UiText

/**
 * [HomeEvent] Describe all the events that should be triggered and consumed by the view.
 * */
sealed interface HomeEvent {
    /**
     * [RequestLocationPermissions] should be called when the general coarse and fine location
     * permission should be requested.
     * */
    data object RequestLocationPermissions : HomeEvent
    /**
     * [NavigateToAppPermissionSettings] should be called when the user permanently denies the
     * location permission and wants to grant it.
     * */
    data object NavigateToAppPermissionSettings : HomeEvent
    /**
     * [Error] should be called when an error occurs during the view process.
     *
     * @param error [UiText] The error that occurred during the view process.
     * */
    data class Error(
        val error : UiText
    ): HomeEvent
}