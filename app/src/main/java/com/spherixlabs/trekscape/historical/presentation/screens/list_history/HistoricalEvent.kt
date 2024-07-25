package com.spherixlabs.trekscape.historical.presentation.screens.list_history

import com.spherixlabs.trekscape.place.domain.model.PlaceData


/**
 * [HistoricalEvent] Describe all the events that should be triggered and consumed by the view.
 * */
sealed interface HistoricalEvent {
    /**
     * the event [OnShowRecommendationOnMap]  triggered when the floating "show on map" button is touched.
     * */
    data class OnShowRecommendationOnMap(val placeData: PlaceData) : HistoricalEvent
}