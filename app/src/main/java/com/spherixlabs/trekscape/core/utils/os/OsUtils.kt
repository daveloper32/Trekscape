package com.spherixlabs.trekscape.core.utils.os

import android.content.Context
import android.location.LocationManager

/**
 * [OsUtils] is a utility class that provides methods for easily handling os utilities.
 * */
object OsUtils {
    /**
     * This function checks if the GPS is enabled on the device.
     *
     * @param context [Context] The context of the application.
     * @return [Boolean] true if the GPS is enabled, false otherwise.
     * */
    fun isGPSEnabled(
        context : Context
    ): Boolean {
        val locationManager = context.getSystemService(
            Context.LOCATION_SERVICE
        ) as LocationManager?
        return locationManager?.isProviderEnabled(LocationManager.GPS_PROVIDER) ?: false
    }
}