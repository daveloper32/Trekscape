package com.spherixlabs.trekscape.core.data.provider

import android.Manifest
import android.content.Context
import com.spherixlabs.trekscape.core.utils.permissions.PermissionUtils
import javax.inject.Inject

/**
 * [ResourceProvider] is a class that provides access to various resources and permissions.
 * */
class ResourceProvider @Inject constructor(
    private val context: Context,
) {
    /**
     * Checks if the [Manifest.permission.ACCESS_FINE_LOCATION] permission is granted.
     *
     * @return [Boolean] true if the [Manifest.permission.ACCESS_FINE_LOCATION] permission is granted, false otherwise.
     * */
    fun isAccessFineLocationPermissionGranted() : Boolean =
        PermissionUtils.isAccessFineLocationPermissionGranted(context)

    /**
     * Checks if the [Manifest.permission.ACCESS_COARSE_LOCATION] permission is granted.
     *
     * @return [Boolean] true if the [Manifest.permission.ACCESS_COARSE_LOCATION] permission is granted, false otherwise.
     * */
    fun isAccessCoarseLocationPermissionGranted() : Boolean =
        PermissionUtils.isAccessCoarseLocationPermissionGranted(context)

    /**
     * Checks if all location permissions are granted. That includes:
     * - [Manifest.permission.ACCESS_COARSE_LOCATION]
     * - [Manifest.permission.ACCESS_FINE_LOCATION]
     *
     * @return [Boolean] true if all location permissions are granted, false otherwise.
     * */
    fun isAllLocationPermissionsGranted() : Boolean =
        PermissionUtils.isAllLocationPermissionsGranted(context)
}