package com.spherixlabs.trekscape.historical.presentation.screens.detail_historical

import com.spherixlabs.trekscape.place.domain.model.PlaceData

/**
 * Describe the state [DetailState] of the place details screen.
 *
 * @property place [PlaceData] representing place details.
 * */
data class DetailState(
    val place : PlaceData = PlaceData()
)