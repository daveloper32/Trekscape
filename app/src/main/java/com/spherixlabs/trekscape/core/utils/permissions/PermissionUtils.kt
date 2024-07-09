package com.spherixlabs.trekscape.core.utils.permissions

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

/**
 * [PermissionUtils] is a utility class that provides methods for handling permissions.
 * */
object PermissionUtils {

    /**
     * Checks if the [Manifest.permission.ACCESS_FINE_LOCATION] permission is granted.
     *
     * @param context [Context] The context used to check the permission.
     * @return [Boolean] true if the [Manifest.permission.ACCESS_FINE_LOCATION] permission is granted, false otherwise.
     * */
    fun isAccessFineLocationPermissionGranted(
        context : Context,
    ) : Boolean =
        ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
            .equals(PackageManager.PERMISSION_GRANTED)

    /**
     * Checks if the [Manifest.permission.ACCESS_COARSE_LOCATION] permission is granted.
     *
     * @param context [Context] The context used to check the permission.
     * @return [Boolean] true if the [Manifest.permission.ACCESS_COARSE_LOCATION] permission is granted, false otherwise.
     * */
    fun isAccessCoarseLocationPermissionGranted(
        context : Context,
    ) : Boolean =
        ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
            .equals(PackageManager.PERMISSION_GRANTED)


    /**
     * Checks if all location permissions are granted. That includes:
     * - [Manifest.permission.ACCESS_COARSE_LOCATION]
     * - [Manifest.permission.ACCESS_FINE_LOCATION]
     *
     * @param context [Context] The context used to check the permission.
     * @return [Boolean] true if all location permissions are granted, false otherwise.
     * */
    fun isAllLocationPermissionsGranted(
        context : Context,
    ) : Boolean = isAccessCoarseLocationPermissionGranted(context) &&
            isAccessFineLocationPermissionGranted(context)
}