package com.spherixlabs.trekscape.place.domain.model

import com.spherixlabs.trekscape.core.utils.constants.Constants.EMPTY_STR
import com.spherixlabs.trekscape.core.utils.constants.Constants.INT_INVALID

/**
 * [PlaceData] is the domain data class that represents a place.
 *
 * @property id [Int] The unique identifier of the place.
 * @property name [String] The name of the place.
 * @property description [String] The description of the place.
 * @property imageUrl [String] The URL of the image associated with the place.
 * */
data class PlaceData(
    val id          : Int    = INT_INVALID,
    val name        : String = EMPTY_STR,
    val description : String = EMPTY_STR,
    val imageUrl    : String = EMPTY_STR,
)
