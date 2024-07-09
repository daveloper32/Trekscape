package com.spherixlabs.trekscape.home.presentation

import com.spherixlabs.trekscape.core.domain.storage.model.permissions.GrantPermissionData

/**
 * [HomeAction] Describe all the actions that can happen in the view.
 * */
sealed interface HomeAction {
    /**
     * [OnScreenStarted] should be called when the screen is fully started.
     * */
    data object OnScreenStarted : HomeAction
    /**
     * [OnGrantLocationPermissions] should be called when user wants to grant location permissions.
     * */
    data object OnGrantLocationPermissions : HomeAction
    /**
     * [OnGrantLocationPermissions] should be called when the user does not grant location permissions.
     * */
    data object OnNotGrantLocationPermissions : HomeAction
    /**
     * [OnLocationPermissionsResult] should be called when the location permissions are requested and
     * the user granted or denied.
     *
     * @param permissionsState [List]<[GrantPermissionData]> a list of permissions that the user granted or denied.
     * */
    data class OnLocationPermissionsResult(
        val permissionsState : List<GrantPermissionData>,
    ) : HomeAction
    /**
     * [OnHistoryClicked] should be called when the history button is clicked.
     *
     * */
    data object OnHistoryClicked : HomeAction
    /**
     * [OnRecommendationsClicked] should be called when the recommendations button is clicked.
     *
     * */
    data object OnRecommendationsClicked : HomeAction
    /**
     * [OnProfileClicked] should be called when the profile button is clicked.
     *
     * */
    data object OnProfileClicked : HomeAction
    /**
     * [OnDismissHistory] should be called when the history should be dismissed.
     *
     * */
    data object OnDismissHistory : HomeAction
    /**
     * [OnDismissProfile] should be called when the profile should be dismissed.
     *
     * */
    data object OnDismissProfile : HomeAction
}