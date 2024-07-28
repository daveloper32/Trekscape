package com.spherixlabs.trekscape.home.presentation

import com.spherixlabs.trekscape.core.domain.model.CoordinatesData
import com.spherixlabs.trekscape.core.utils.constants.Constants.EMPTY_STR
import com.spherixlabs.trekscape.core.utils.maps.MapsUtils
import com.spherixlabs.trekscape.home.domain.enums.LocationPreference
import com.spherixlabs.trekscape.place.domain.model.PlaceData

/**
 * Describe the state [HomeState] of the home screen.
 *
 * @param isLocationPermissionBeingRequested [Boolean] True if the location permission is being requested, false otherwise.
 * @param isMyLocationEnabled [Boolean] True if the my location is enabled, false otherwise.
 * @param isGeneralPreferencesBeingRequested [Boolean] True if the general preferences are being requested, false otherwise.
 * @param isLocationPreferencesBeingRequested [Boolean] True if the recommendations based on location is being requested, false otherwise.
 * @param isLocationPreferencesBeingEdited [Boolean] True if the recommendations based on location is being edited, false otherwise.
 * @param isDonTAskAgainLocationPreferencesSelected [Boolean] True if the user has selected to not ask again, false otherwise.
 * @param isEnableGPSBeingRequested [Boolean] True if the GPS is being requested, false otherwise.
 * @param currentLocationPreference [LocationPreference] The current location preference.
 * @param isShowingHistory [Boolean] True if the history is being shown, false otherwise.
 * @param isShowingProfile [Boolean] True if the profile is being shown, false otherwise.
 * @param currentMapCameraLocation [CoordinatesData] The current map camera location.
 * @param isLoadingRecommendations [Boolean] True if the recommendations are being loaded, false otherwise.
 * @param placeRecommendations [List]<[PlaceData]> The list of place recommendations.
 * @param userName [String] this is the username.
 * @param attemptsAvailable [Int] the missing attempts.
 * @param timeRemaining [String] the remaining time to be able to have 5 attempts again.
 * @param isShowingPlaceRecommendationDetails [Boolean] True if the place recommendation details are being shown, false otherwise.
 * @param placeDetails [PlaceData] The place recommendation details.
 * */
data class HomeState(
    val isLocationPermissionBeingRequested        : Boolean = false,
    val isMyLocationEnabled                       : Boolean = false,
    val isGeneralPreferencesBeingRequested        : Boolean = false,
    val isLocationPreferencesBeingRequested       : Boolean = false,
    val isLocationPreferencesBeingEdited          : Boolean = false,
    val isDonTAskAgainLocationPreferencesSelected : Boolean = false,
    val isEnableGPSBeingRequested                 : Boolean = false,
    val currentLocationPreference                 : LocationPreference = LocationPreference.ALL_WORLD,
    val isShowingHistory                          : Boolean = false,
    val isShowingProfile                          : Boolean = false,
    val currentMapCameraLocation                  : CoordinatesData = MapsUtils.DEFAULT_COORDINATES,
    val isLoadingRecommendations                  : Boolean = false,
    val placeRecommendations                      : List<PlaceData> = emptyList(),
    val userName                                  : String  = EMPTY_STR,
    val attemptsAvailable                         : Int  = 0,
    val timeRemaining                             : String  = "",
    val isShowingPlaceRecommendationDetails       : Boolean = false,
    val placeDetails                              : PlaceData? = null,
)