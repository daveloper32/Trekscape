package com.spherixlabs.trekscape.home.presentation

import com.spherixlabs.trekscape.core.utils.coordinates.model.CoordinatesData
import com.spherixlabs.trekscape.core.utils.maps.MapsUtils
import com.spherixlabs.trekscape.home.domain.enums.LocationPreference
import com.spherixlabs.trekscape.recommendations.domain.model.PlaceRecommendation

/**
 * Describe the state [HomeState] of the name request screen.
 *
 * @param isLocationPermissionBeingRequested [Boolean] True if the location permission is being requested, false otherwise.
 * @param isMyLocationEnabled [Boolean] True if the my location is enabled, false otherwise.
 * @param isLocationPreferencesBeingRequested [Boolean] True if the recommendations based on location is being requested, false otherwise.
 * @param isDonTAskAgainLocationPreferencesSelected [Boolean] True if the user has selected to not ask again, false otherwise.
 * @param currentLocationPreference [LocationPreference] The current location preference.
 * @param isShowingHistory [Boolean] True if the history is being shown, false otherwise.
 * @param isShowingProfile [Boolean] True if the profile is being shown, false otherwise.
 * @param currentMapCameraLocation [CoordinatesData] The current map camera location.
 * @param isLoadingRecommendations [Boolean] True if the recommendations are being loaded, false otherwise.
 * @param placeRecommendations [List]<[PlaceRecommendation]> The list of place recommendations.
 * @param userName [String] this is the username.
 * */
data class HomeState(
    val isLocationPermissionBeingRequested        : Boolean = false,
    val isMyLocationEnabled                       : Boolean = false,
    val isLocationPreferencesBeingRequested       : Boolean = false,
    val isDonTAskAgainLocationPreferencesSelected : Boolean = false,
    val currentLocationPreference                 : LocationPreference = LocationPreference.ALL_WORLD,
    val isShowingHistory                          : Boolean = false,
    val isShowingProfile                          : Boolean = false,
    val currentMapCameraLocation                  : CoordinatesData = MapsUtils.DEFAULT_COORDINATES,
    val isLoadingRecommendations                  : Boolean = false,
    val placeRecommendations                      : List<PlaceRecommendation> = emptyList(),
    val userName                                  : String  = EMPTY_STR,
)