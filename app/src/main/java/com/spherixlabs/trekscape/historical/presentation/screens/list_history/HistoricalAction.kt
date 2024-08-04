package com.spherixlabs.trekscape.historical.presentation.screens.list_history

import com.spherixlabs.trekscape.place.domain.model.PlaceData

/**
 * [HistoricalAction] Describe all the actions that can happen in the view.
 * */
sealed interface HistoricalAction {
    /**
     * [OnSetOrUnsetPlaceAsFavorite] should be called when the place should be set or unset as favorite.
     *
     * @param place [PlaceData] The place to set or unset as favorite.
     * */
    data class OnSetOrUnsetPlaceAsFavorite(
        val place : PlaceData,
    ): HistoricalAction
    /**
     * [OnHistoricalClicked] should be called when the profile button is clicked.
     *
     * @param place [PlaceData] The place that was clicked.
     * */
    data class OnHistoricalClicked(
        val place : PlaceData,
    ) : HistoricalAction
    /**
     * [OnShowSomePlaceOnMapClicked] should be called when a place should be shown on map.
     *
     * @param place [PlaceData] the place to show.
     * */
    data class OnShowSomePlaceOnMapClicked(
        val place : PlaceData
    ): HistoricalAction
    /**
     * [OnDismissDetailHistorical] should be called when the profile should be dismissed.
     *
     * */
    data object OnDismissDetailHistorical : HistoricalAction
    /**
     * [ShowOnlyFavorites] Should be called when the user selects to show only favorites in history.
     *
     * */
    data class ShowOnlyFavorites(val showOnlyFavorites : Boolean) : HistoricalAction
}