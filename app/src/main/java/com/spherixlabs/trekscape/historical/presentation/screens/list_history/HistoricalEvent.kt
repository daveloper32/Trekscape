package com.spherixlabs.trekscape.historical.presentation.screens.list_history

import com.spherixlabs.trekscape.place.domain.model.PlaceData

/**
 * [HistoricalEvent] Describe all the events that should be triggered and consumed by the view.
 * */
sealed interface HistoricalEvent {
    /**
     * [OnShowSomePlaceOnMap] should be triggered when the floating "show on map" button is touched.
     *
     * @param place [PlaceData] The place that was clicked.
     * */
    data class OnShowSomePlaceOnMap(
        val place : PlaceData
    ) : HistoricalEvent
}