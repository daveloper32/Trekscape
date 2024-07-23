package com.spherixlabs.trekscape.core.utils.maps

import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.spherixlabs.trekscape.core.domain.model.CoordinatesData

/**
 * [MapsUtils] is a utility class that provides methods for easily handling maps utilities.
 * */
object MapsUtils {
    /** Initial coordinates for the map */
    val DEFAULT_COORDINATES = CoordinatesData(
        latitude  = 4.639794,
        longitude = -74.094507,
    )
    /** Initial zoom level for the map */
    const val DEFAULT_ZOOM = 13f
    /** Initial padding for the map */
    const val DEFAULT_PADDING = 70
    /**
     * Converts a [CoordinatesData] to a [LatLng] object.
     *
     * @param coordinates [CoordinatesData] The coordinates to convert.
     * @return [LatLng] The converted [LatLng] object.
     * */
    fun fromCoordinatesDataToLatLng(
        coordinates : CoordinatesData,
    ): LatLng = LatLng(
        coordinates.latitude,
        coordinates.longitude
    )
    /**
     * Converts a [CoordinatesData] to a [LatLng] object.
     *
     * @param coordinates [CoordinatesData]? The coordinates to convert.
     * @return [LatLng] The converted [LatLng]? object.
     * */
    fun fromNullCoordinatesDataToLatLng(
        coordinates : CoordinatesData?,
    ): LatLng? = if (coordinates != null) {
        LatLng(
            coordinates.latitude,
            coordinates.longitude
        )
    } else {
        null
    }
    /**
     * Converts a [CoordinatesData] to a [CameraPosition] object.
     *
     * @param coordinates [CoordinatesData] The coordinates to convert.
     * @*param zoom [Float] The zoom level for the camera position.
     * @return [CameraPosition] The converted [CameraPosition] object.
     * */
    fun fromCoordinatesDataToCameraPosition(
        coordinates : CoordinatesData,
        zoom        : Float = DEFAULT_ZOOM,
    ): CameraPosition = CameraPosition.fromLatLngZoom(
        fromCoordinatesDataToLatLng(coordinates),
        zoom
    )
    /**
     * Converts a [CoordinatesData] to a [CameraUpdate] object.
     *
     * @param coordinates [CoordinatesData] The coordinates to convert.
     * @param zoom [Float] The zoom level for the camera position.
     * @return [CameraUpdate] The converted [CameraUpdate] object.
     * */
    fun fromCoordinatesDataToCameraUpdate(
        coordinates : CoordinatesData,
        zoom        : Float = DEFAULT_ZOOM,
    ): CameraUpdate = CameraUpdateFactory.newLatLngZoom(
        fromCoordinatesDataToLatLng(coordinates),
        zoom
    )
    /**
     * Converts a [List]<[CoordinatesData]> to a [CameraUpdate] object.
     *
     * @param coordinates [List]<[CoordinatesData]> The coordinates to convert.
     * @*param padding [Int] The padding for the camera position.
     * @return [CameraUpdate] The converted [CameraUpdate] object.
     * */
    fun fromCoordinatesDataToCameraUpdate(
        coordinates : List<CoordinatesData>,
        padding     : Int = DEFAULT_PADDING,
    ): CameraUpdate {
        if (coordinates.size == 1) {
            return fromCoordinatesDataToCameraUpdate(
                coordinates.first(),
            )
        }
        val latLngBoundsBuilder: LatLngBounds.Builder = LatLngBounds.builder()
        coordinates.forEach {
            latLngBoundsBuilder.include(
                fromCoordinatesDataToLatLng(it)
            )
        }
        return CameraUpdateFactory.newLatLngBounds(
            latLngBoundsBuilder.build(),
            padding
        )
    }
}