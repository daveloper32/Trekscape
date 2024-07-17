package com.spherixlabs.trekscape.core.utils.coordinates

import android.annotation.SuppressLint
import android.content.Context
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import com.spherixlabs.trekscape.core.utils.coordinates.model.CoordinatesData
import com.spherixlabs.trekscape.core.utils.permissions.PermissionUtils
import kotlinx.coroutines.tasks.await

/**
 * [CoordinatesUtils] is a utility class that provides methods for easily handling coordinates.
 * */
object CoordinatesUtils {
    /**MAXIMUM_LATITUDE_VALUE = 90.0000000*/
    private const val MAXIMUM_LATITUDE_VALUE  : Double = 90.0000000
    /**MINIMUM_LATITUDE_VALUE = -90.0000000*/
    private const val MINIMUM_LATITUDE_VALUE  : Double = -90.0000000
    /**MAXIMUM_LONGITUDE_VALUE = 180.0000000*/
    private const val MAXIMUM_LONGITUDE_VALUE : Double = 180.0000000
    /**MINIMUM_LONGITUDE_VALUE = -180.0000000*/
    private const val MINIMUM_LONGITUDE_VALUE : Double = -180.0000000
    /**
     * Gets the current coordinates of the user.
     *
     * @param context [Context] The application context.
     * @return [CoordinatesData]? The current coordinates of the user.
     * */
    @SuppressLint("MissingPermission")
    suspend fun getCurrentCoordinates(
        context : Context
    ): CoordinatesData? {
        return try {
            if (!PermissionUtils.isAllLocationPermissionsGranted(context)) {
                return null
            }
            val location = LocationServices.getFusedLocationProviderClient(context)
                .lastLocation
                .await() ?: return null
            val coordinates = CoordinatesData(location.latitude, location.longitude)
            if (!isValidCoordinates(coordinates)) {
                return null
            }
            coordinates
        } catch (e: Exception) {
            null
        }
    }

    /**
     * Validates the if the latitude value is valid.
     *
     * @param latitude [String]? the latitude value to be validated
     * @return [Boolean] true if the latitude value is valid, false otherwise
     * */
    fun isValidLatitude(
        latitude : String? = null
    ): Boolean {
        return try {
            val latitudeValue: Double? = latitude?.toDoubleOrNull()
            if (latitudeValue != null) {
                latitudeValue in MINIMUM_LATITUDE_VALUE..MAXIMUM_LATITUDE_VALUE
            } else {
                false
            }
        } catch (e: Exception) {
            false
        }
    }

    /**
     * Validates the if the latitude value is valid.
     *
     * @param latitude [Double]? the latitude value to be validated
     * @return [Boolean] true if the latitude value is valid, false otherwise
     * */
    fun isValidLatitude(
        latitude : Double? = null
    ): Boolean {
        return try {
            if (latitude != null) {
                latitude in MINIMUM_LATITUDE_VALUE..MAXIMUM_LATITUDE_VALUE
            } else {
                false
            }
        } catch (e: Exception) {
            false
        }
    }

    /**
     * Validates the if the longitude value is valid.
     *
     * @param longitude [String]? the longitude value to be validated
     * @return [Boolean] true if the longitude value is valid, false otherwise
     * */
    fun isValidLongitude(
        longitude : String? = null
    ): Boolean {
        return try {
            val longitudeValue: Double? = longitude?.toDoubleOrNull()
            if (longitudeValue != null) {
                longitudeValue in MINIMUM_LONGITUDE_VALUE..MAXIMUM_LONGITUDE_VALUE
            } else {
                false
            }
        } catch (e: Exception) {
            false
        }
    }

    /**
     * Validates the if the longitude value is valid.
     *
     * @param longitude [Double]? the longitude value to be validated
     * @return [Boolean] true if the longitude value is valid, false otherwise
     * */
    fun isValidLongitude(
        longitude : Double? = null
    ): Boolean {
        return try {
            if (longitude != null) {
                longitude in MINIMUM_LONGITUDE_VALUE..MAXIMUM_LONGITUDE_VALUE
            } else {
                false
            }
        } catch (e: Exception) {
            false
        }
    }

    /**
     * Validates the if the coordinates are valid.
     *
     * @param coordinates [LatLng]? the coordinates to be validated
     * @return [Boolean] true if the coordinates are valid, false otherwise
     * */
    fun isValidCoordinates(
        coordinates : LatLng?
    ): Boolean {
        return try {
            if (coordinates != null) {
                isValidLatitude(coordinates.latitude) &&
                isValidLongitude(coordinates.longitude)
            } else {
                false
            }
        } catch (e: Exception) {
            false
        }
    }

    /**
     * Validates the if the coordinates are valid.
     *
     * @param coordinates [CoordinatesData]? the coordinates to be validated
     * @return [Boolean] true if the coordinates are valid, false otherwise
     * */
    fun isValidCoordinates(
        coordinates : CoordinatesData?
    ): Boolean {
        return try {
            if (coordinates != null) {
                isValidLatitude(coordinates.latitude) &&
                isValidLongitude(coordinates.longitude)
            } else {
                false
            }
        } catch (e: Exception) {
            false
        }
    }
}