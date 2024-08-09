package com.spherixlabs.trekscape.recommendations.domain.utils

import com.spherixlabs.trekscape.core.utils.constants.Constants.EMPTY_STR
import com.spherixlabs.trekscape.activity.domain.model.ActivityData
import com.spherixlabs.trekscape.recommendations.domain.model.PlaceActivityRecommendation

/**
 * Convert [PlaceActivityRecommendation] to [ActivityData]
 * */
fun PlaceActivityRecommendation.toActivityData(
    placeId : String = EMPTY_STR,
): ActivityData = ActivityData(
    placeId     = placeId,
    name        = this.name,
    description = this.description,
)