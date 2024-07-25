package com.spherixlabs.trekscape.core.utils.coordinates

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import com.spherixlabs.trekscape.core.domain.model.CoordinatesData
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
     * Calculates the distance between two geographic coordinates.
     *
     * This function takes the latitude and longitude of two points
     * and returns the distance between them in meters.
     *
     * @param lat1 The latitude of the first point in decimal degrees.
     * @param lon1 The longitude of the first point in decimal degrees.
     * @param lat2 The latitude of the second point in decimal degrees.
     * @param lon2 The longitude of the second point in decimal degrees.
     * @return The distance between the two points in meters.
     */
    fun calculateDistance(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Float {
        val startLocation = Location("").apply {
            latitude  = lat1
            longitude = lon1
        }
        val endLocation = Location("").apply {
            latitude  = lat2
            longitude = lon2
        }
        return startLocation.distanceTo(endLocation)
    }
    /**
     * Formats a given distance in meters
     *
     * This function converts the distance from meters to kilometers if the distance is
     * greater than or equal to 1000 meters. The formatted string will display the distance
     * with two decimal places in kilometers (e.g., "1.23 km") or as an integer in meters
     * (e.g., "123 m").
     *
     * @param distanceInMeters The distance in meters to be formatted.
     * @return A string representing the formatted distance in either kilometers or meters.
     */
    @SuppressLint("DefaultLocale")
    fun formatDistance(distanceInMeters: Float): String {
        return if (distanceInMeters >= 1000) {
            String.format("%.2f km", distanceInMeters / 1000)
        } else {
            String.format("%.0f m", distanceInMeters)
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

    /**
     * [SquaredPolygon] is a data class that represents a squared polygon of coordinates.
     *
     * @property northEastCoordinate [CoordinatesData] The north-east coordinate of the polygon.
     * @property southEastCoordinate [CoordinatesData] The south-east coordinate of the polygon.
     * @property northWestCoordinate [CoordinatesData] The north-west coordinate of the polygon.
     * @property southWestCoordinate [CoordinatesData] The south-west coordinate of the polygon.
     * */
    data class SquaredPolygon(
        val northEastCoordinate : CoordinatesData,
        val southEastCoordinate : CoordinatesData,
        val northWestCoordinate : CoordinatesData,
        val southWestCoordinate : CoordinatesData,
    ) {
        /**
         * This function orders the coordinates in a clockwise order.
         *
         * @return [List]<[CoordinatesData]> The ordered coordinates.
         * */
        fun order(): List<CoordinatesData> = listOf(
            northEastCoordinate,
            northWestCoordinate,
            southWestCoordinate,
            southEastCoordinate,
        )
    }

    /**
     * This function generates a squared polygon above some coordinates with a given padding.
     *
     * @param coordinates [List]<[CoordinatesData]> The coordinates to generate the polygon on.
     * Couldn't be empty.
     * @param padding [Double] The padding to be applied to the polygon.
     * @return [SquaredPolygon]? The generated squared polygon.
     * */
    fun generateSquaredPolygonOnSomeCoordinates(
        coordinates : List<CoordinatesData>,
        padding     : Double = 0.017,
    ): SquaredPolygon? {
        if (coordinates.isEmpty()) {
            return null
        }

        var minLatitude  : Double = coordinates.first().latitude
        var maxLatitude  : Double = coordinates.first().latitude
        var minLongitude : Double = coordinates.first().longitude
        var maxLongitude : Double = coordinates.first().longitude

        for (coordinate in coordinates) {
            minLatitude  = minOf(minLatitude, coordinate.latitude)
            maxLatitude  = maxOf(maxLatitude, coordinate.latitude)
            minLongitude = minOf(minLongitude, coordinate.longitude)
            maxLongitude = maxOf(maxLongitude, coordinate.longitude)
        }

        val latitudePadding  : Double = padding / 2
        val longitudePadding : Double = padding / 2

        val northEastCoordinate: CoordinatesData = CoordinatesData(
            latitude  = maxLatitude + latitudePadding,
            longitude = maxLongitude + longitudePadding,
        )
        val southEastCoordinate: CoordinatesData = CoordinatesData(
            latitude  = minLatitude - latitudePadding,
            longitude = minLongitude + longitudePadding,
        )
        val northWestCoordinate: CoordinatesData = CoordinatesData(
            latitude  = maxLatitude + latitudePadding,
            longitude = maxLongitude - longitudePadding,
        )
        val southWestCoordinate: CoordinatesData = CoordinatesData(
            latitude  = minLatitude - latitudePadding,
            longitude = minLongitude - longitudePadding,
        )

        return SquaredPolygon(
            northEastCoordinate = northEastCoordinate,
            southEastCoordinate = southEastCoordinate,
            northWestCoordinate = northWestCoordinate,
            southWestCoordinate = southWestCoordinate,
        )
    }
}