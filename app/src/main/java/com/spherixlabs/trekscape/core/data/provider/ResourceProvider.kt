package com.spherixlabs.trekscape.core.data.provider

import android.Manifest
import android.content.Context
import android.graphics.Bitmap
import com.spherixlabs.trekscape.core.utils.coordinates.CoordinatesUtils
import com.spherixlabs.trekscape.core.domain.model.CoordinatesData
import com.spherixlabs.trekscape.core.utils.bitmap.BitmapUtils
import com.spherixlabs.trekscape.core.utils.os.OsUtils
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

    /**
     * Gets the current coordinates of the user.
     *
     * @return [CoordinatesData]? The current coordinates of the user.
     * */
    suspend fun getCurrentCoordinates(): CoordinatesData? =
        CoordinatesUtils.getCurrentCoordinates(context)

    /**
     * This function checks if the GPS is enabled on the device.
     *
     * @return [Boolean] true if the GPS is enabled, false otherwise.
     * */
    fun isGPSEnabled(): Boolean = OsUtils.isGPSEnabled(context)

    /**
     * This function converts an image url to a [Bitmap] object.
     *
     * @param imageUrl [String] The url of the image.
     * @return [Bitmap] The converted [Bitmap] object.
     * */
    suspend fun fromImageUrlToBitmap(
        imageUrl : String,
    ): Bitmap? = BitmapUtils
        .fromImageUrlToBitmap(
            imageUrl = imageUrl,
            context  = context,
        )
}