package com.spherixlabs.trekscape.recommendations.domain.model

import com.spherixlabs.trekscape.core.domain.model.CoordinatesData

/**
 * [PlaceRecommendation] is a data class that represents a place recommendation.
 *
 * @property name [String] The name of the place.
 * @property description [String] The description of the place.
 * @property imageUrl [String] The URL of the image associated with the place.
 * @property location [CoordinatesData] The location of the place on the map.
 * */
data class PlaceRecommendation(
    val name        : String,
    val description : String,
    val imageUrl    : String,
    val location    : CoordinatesData,
)