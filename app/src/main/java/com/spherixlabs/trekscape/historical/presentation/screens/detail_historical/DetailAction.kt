package com.spherixlabs.trekscape.historical.presentation.screens.detail_historical

import com.spherixlabs.trekscape.place.domain.model.PlaceData

/**
 * [DetailAction] Describe all the actions that can happen in the view.
 * */
sealed interface DetailAction {
    /**
     * [OnDataReceived] should be called when the some init data is received from screen.
     *
     * @param place [PlaceData] the place received.
     * */
    data class OnDataReceived(
        val place        : PlaceData,
    ) : DetailAction
    /**
     * [OnSetOrUnsetPlaceAsFavorite] should be called when the place should be set or unset as favorite.
     * */
    data object OnSetOrUnsetPlaceAsFavorite: DetailAction
    /**
     * [OnShowPlaceOnMapClicked] should be called when the place should be shown on map.
     * */
    data object OnShowPlaceOnMapClicked: DetailAction
    /**
     * [OnDeletePlaceClicked] should be called when the place should be deleted.
     * */
    data object OnDeletePlaceClicked: DetailAction
}