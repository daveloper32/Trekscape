package com.spherixlabs.trekscape.historical.presentation.screens.list_history

import com.spherixlabs.trekscape.place.domain.model.PlaceData


/**
 * [HistoricalAction] Describe all the actions that can happen in the view.
 * */
sealed interface HistoricalAction {
    /**
     * [OnHistoricalClicked] should be called when the profile button is clicked.
     *
     * @param place [PlaceData] The place that was clicked.
     * */
    data class OnHistoricalClicked(
        val place : PlaceData,
    ) : HistoricalAction
    /**
     * [OnShowRecommendationOnMap] should be called when the floating show on map button is clicked.
     *
     * */
    data class OnShowRecommendationOnMap(val placeData: PlaceData) : HistoricalAction
    /**
     * [OnDismissDetailHistorical] should be called when the profile should be dismissed.
     *
     * */
    data object OnDismissDetailHistorical : HistoricalAction
}