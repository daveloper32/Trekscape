package com.spherixlabs.trekscape.core.domain.storage

/**
 * [PermissionsStateStorage] describes all possible functions that should be implemented in order to get
 * and/or save permission state related data in storage.
 * */
interface PermissionsStateStorage {
    /**
     * It's a set/get function to know if at some point in the app, the shouldShowRationale of
     * [android.Manifest.permission.ACCESS_COARSE_LOCATION] permission was set to true.
     *
     * @return true if the shouldShowRationale of [android.Manifest.permission.ACCESS_COARSE_LOCATION]
     * was already triggered, false otherwise.
     * */
    var isCoarseLocationRationaleShown : Boolean
    /**
     * It's a set/get function to know if at some point in the app, the shouldShowRationale of
     * [android.Manifest.permission.ACCESS_FINE_LOCATION] permission was set to true.
     *
     * @return true if the shouldShowRationale of [android.Manifest.permission.ACCESS_FINE_LOCATION]
     * was already triggered, false otherwise.
     * */
    var isFineLocationRationaleShown : Boolean
    /**
     * It's a set/get function to know if at some point in the app, the
     * [android.Manifest.permission.ACCESS_COARSE_LOCATION] permission was permanently declined.
     *
     * @return true if the [android.Manifest.permission.ACCESS_COARSE_LOCATION] is permanently
     * declined, otherwise false.
     * */
    var isCoarseLocationPermanentlyDeclined : Boolean
    /**
     * It's a set/get function to know if at some point in the app, the
     * [android.Manifest.permission.ACCESS_FINE_LOCATION] permission was permanently declined.
     *
     * @return true if the [android.Manifest.permission.ACCESS_FINE_LOCATION] is permanently declined,
     * otherwise false.
     * */
    var isFineLocationPermanentlyDeclined : Boolean
}