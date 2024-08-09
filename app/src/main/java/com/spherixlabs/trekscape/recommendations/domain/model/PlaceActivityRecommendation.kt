package com.spherixlabs.trekscape.recommendations.domain.model

/**
 * [PlaceActivityRecommendation] is a data class that represents a place recommendation.
 *
 * @property name [String] The name of the activity.
 * @property description [String] The description of the activity.
 * */
data class PlaceActivityRecommendation(
    val name        : String,
    val description : String,
)