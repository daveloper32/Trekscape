package com.spherixlabs.trekscape.home.presentation

import com.spherixlabs.trekscape.core.domain.storage.model.permissions.GrantPermissionData
import com.spherixlabs.trekscape.home.domain.enums.LocationPreference
import com.spherixlabs.trekscape.place.domain.model.PlaceData

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
     * [OnAboutClicked] should be called when the about button is clicked.
     *
     * */
    data object OnAboutClicked : HomeAction
    /**
     * [OnDismissAbout] should be called when the about info should be dismissed.
     *
     * */
    data object OnDismissAbout : HomeAction
    /**
     * [OnDismissHistory] should be called when the history should be dismissed.
     *
     * */
    data object OnDismissHistory : HomeAction
    /**
     * [OnDonTAskAgainLocationPreferencesClicked] should be called when the Don't ask again location
     * preferences function is clicked by the user.
     *
     * */
    data object OnDonTAskAgainLocationPreferencesClicked : HomeAction
    /**
     * [OnEnableGPSClicked] should be called when the enable GPS button is clicked.
     * */
    data object OnEnableGPSClicked : HomeAction
    /**
     * [OnRecommendInAllWorldClicked] should be called when the recommend in all world button is clicked.
     * */
    data object OnRecommendInAllWorldClicked : HomeAction
    /**
     * [OnDismissLocationPreferences] should be called when the location preferences should be dismissed.
     *
     * */
    data object OnDismissLocationPreferences : HomeAction
    /**
     * [OnDismissProfile] should be called when the profile should be dismissed.
     *
     * */
    data object OnDismissProfile : HomeAction
    /**
     * [OnLocationPreferencesSetupFilled] should be called when the location preferences are filled
     * by the user.
     *
     * @param locationPreference [LocationPreference] the location preference chose by the user.
     * */
    data class OnLocationPreferencesSetupFilled(
        val locationPreference : LocationPreference = LocationPreference.ALL_WORLD,
    ) : HomeAction
    /**
     * [OnSomePlaceRecommendationClicked] should be called when a place recommendation is clicked on
     * the map.
     *
     * @param place [PlaceData] the place clicked.
     * */
    data class OnSomePlaceRecommendationClicked(
        val place : PlaceData
    ): HomeAction
    /**
     * [OnShowSomePlaceOnMapClicked] should be called when a place should be shown on map.
     *
     * @param place [PlaceData] the place to show.
     * */
    data class OnShowSomePlaceOnMapClicked(
        val place : PlaceData
    ): HomeAction
    /**
     * [OnDismissPlaceRecommendationDetails] should be called when the place recommendation should
     * be dismissed.
     * */
    data object OnDismissPlaceRecommendationDetails : HomeAction
    /**
     * [OnEditGeneralPreferences] should be called when the user wants to edit the general preferences
     * */
    data object OnEditGeneralPreferences : HomeAction
    /**
     * [OnDismissGeneralPreferences] should be called when the general preferences should be dismissed.
     * */
    data object OnDismissGeneralPreferences : HomeAction
    /**
     * [OnEditLocationPreferences] should be called when the user wants to edit the location preferences
     * */
    data object OnEditLocationPreferences : HomeAction
    /**
     * [OnGoToEditApiKey] should be called when the user wants to edit the api key
     * */
    data object OnGoToEditApiKey : HomeAction
    /**
     * [OnDismissEditApiKey] should be called when the edit apikey dialog should be dismissed.
     * */
    data object OnDismissEditApiKey : HomeAction
    /**
     * [OnDismissEditApiKey] should be called when the warning attempts dialog should be dismissed.
     * */
    data object OnDismissWarningAttempts : HomeAction
    /**
     * [OnGrantWarningAttempts] should be called when the user wants to edit the api key
     * */
    data object OnGrantWarningAttempts : HomeAction
}