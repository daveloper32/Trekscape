package com.spherixlabs.trekscape.home.presentation

import com.spherixlabs.trekscape.core.utils.coordinates.model.CoordinatesData
import com.spherixlabs.trekscape.core.utils.maps.MapsUtils

/**
 * Describe the state [HomeState] of the name request screen.
 *
 * @param isLocationPermissionBeingRequested [Boolean] True if the location permission is being requested, false otherwise.
 * @param isMyLocationEnabled [Boolean] True if the my location is enabled, false otherwise.
 * @param isShowingHistory [Boolean] True if the history is being shown, false otherwise.
 * @param isShowingProfile [Boolean] True if the profile is being shown, false otherwise.
 * @param currentMapCameraLocation [CoordinatesData] The current map camera location.
 * */
data class HomeState(
    val isLocationPermissionBeingRequested : Boolean = false,
    val isMyLocationEnabled                : Boolean = false,
    val isShowingHistory                   : Boolean = false,
    val isShowingProfile                   : Boolean = false,
    val currentMapCameraLocation           : CoordinatesData = MapsUtils.DEFAULT_COORDINATES,
)
