package com.spherixlabs.trekscape.place.domain.model

import android.graphics.Bitmap
import com.spherixlabs.trekscape.core.domain.model.CoordinatesData
import com.spherixlabs.trekscape.core.utils.constants.Constants.EMPTY_STR
import com.spherixlabs.trekscape.core.utils.constants.Constants.INT_ZERO

/**
 * [PlaceData] is the domain data class that represents a place.
 *
 * @property id [Int] The unique identifier of the place.
 * @property name [String] The name of the place.
 * @property description [String] The description of the place.
 * @property imageUrl [String] The URL of the image associated with the place.
 * @property coordinates [CoordinatesData] The coordinates of the place.
 * @property isFavorite [Boolean] Whether the place is a favorite or not.
 * @property missingMeters [String] The missing meters of the place.
 * @property icon [Bitmap] The icon associated with the place.
 * */
data class PlaceData(
    val id            : Int    = INT_ZERO,
    val name          : String = EMPTY_STR,
    val description   : String = EMPTY_STR,
    val imageUrl      : String = EMPTY_STR,
    val coordinates   : CoordinatesData = CoordinatesData(0.0, 0.0),
    val isFavorite    : Boolean = false,
    var missingMeters : String = EMPTY_STR,
    val icon          : Bitmap? = null,
)