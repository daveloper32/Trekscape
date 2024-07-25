package com.spherixlabs.trekscape.historical.presentation.screens.detail_historical



/**
 * [DetailAction] Describe all the actions that can happen in the view.
 * */
sealed interface DetailAction {
    /**
     * [OnShowRecommendationOnMap] should be called when the floating button is clicked.
     *
     * */
    data object OnShowRecommendationOnMap : DetailAction
}