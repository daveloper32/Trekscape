package com.spherixlabs.trekscape.recommendations.domain.model

import com.spherixlabs.trekscape.core.domain.model.CoordinatesData
import com.spherixlabs.trekscape.core.utils.constants.Constants.EMPTY_STR

/**
 * [PlaceRecommendation] is a data class that represents a place recommendation.
 *
 * @property name [String] The name of the place.
 * @property description [String] The description of the place.
 * @property imageUrl [String] The URL of the image associated with the place.
 * @property location [CoordinatesData] The location of the place on the map.
 * @property missingMeters [String] The missing meters from the user to the place.
 * */
data class PlaceRecommendation(
    val name          : String,
    val description   : String,
    val imageUrl      : String,
    val location      : CoordinatesData,
    var missingMeters : String = EMPTY_STR,
)