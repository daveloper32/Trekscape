package com.spherixlabs.trekscape.historical.presentation.screens.detail_historical

import com.spherixlabs.trekscape.activity.domain.model.ActivityData
import com.spherixlabs.trekscape.place.domain.model.PlaceData

/**
 * Describe the state [DetailState] of the place details screen.
 *
 * @property place [PlaceData] representing place details.
 * @property isLoadingActivities [Boolean] indicating whether activities are being loaded.
 * @property activities [List] of [ActivityData] representing activities associated with the place.
 * */
data class DetailState(
    val place               : PlaceData = PlaceData(),
    val isLoadingActivities : Boolean = false,
    val activities          : List<ActivityData> = emptyList(),
)