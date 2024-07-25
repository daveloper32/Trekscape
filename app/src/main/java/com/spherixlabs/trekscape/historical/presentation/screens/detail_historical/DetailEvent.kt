package com.spherixlabs.trekscape.historical.presentation.screens.detail_historical


/**
 * [DetailEvent] Describe all the events that should be triggered and consumed by the view.
 * */
sealed interface DetailEvent {
    /**
     * the event [OnShowRecommendationOnMap]  triggered when the floating "show on map" button is touched.
     * */
    data object OnShowRecommendationOnMap : DetailEvent
}