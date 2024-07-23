package com.spherixlabs.trekscape.recommendations.domain.utils

import com.spherixlabs.trekscape.place.domain.model.PlaceData
import com.spherixlabs.trekscape.recommendations.domain.model.PlaceRecommendation

/**
 * Convert [PlaceRecommendation] to [PlaceData]
 * */
fun PlaceRecommendation.toPlaceData(): PlaceData = PlaceData(
    name        = this.name,
    description = this.description,
    imageUrl    = this.imageUrl,
    coordinates = this.location,
)