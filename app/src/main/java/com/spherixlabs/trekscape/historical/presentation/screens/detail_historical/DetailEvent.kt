package com.spherixlabs.trekscape.historical.presentation.screens.detail_historical

import com.spherixlabs.trekscape.place.domain.model.PlaceData

/**
 * [DetailEvent] Describe all the events that should be triggered and consumed by the view.
 * */
sealed interface DetailEvent {
    /**
     * [OnShowSomePlaceOnMap] should be triggered when the floating "show on map" button is touched.
     *
     * @param place [PlaceData] The place that was clicked.
     * */
    data class OnShowSomePlaceOnMap(
        val place : PlaceData
    ) : DetailEvent
    /**
     * [OnDismiss] should be triggered when the dialog is dismissed.
     * */
    data object OnDismiss : DetailEvent
}