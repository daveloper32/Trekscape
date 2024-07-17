package com.spherixlabs.trekscape.home.domain.utils

import com.spherixlabs.trekscape.core.utils.constants.Constants.EMPTY_STR
import com.spherixlabs.trekscape.historical.domain.model.HistoricalModel
import com.spherixlabs.trekscape.recommendations.domain.model.PlaceRecommendation

/**
 * Convert a [PlaceRecommendation] to [HistoricalModel]
 * */
fun PlaceRecommendation.toHistoricalModel(): HistoricalModel = HistoricalModel(
    name          = this.name,
    description   = this.description,
    urlImage      = this.imageUrl,
    missingMeters = EMPTY_STR,
)