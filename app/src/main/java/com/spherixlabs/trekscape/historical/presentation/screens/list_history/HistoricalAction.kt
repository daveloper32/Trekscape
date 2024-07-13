package com.spherixlabs.trekscape.historical.presentation.screens.list_history

import com.spherixlabs.trekscape.historical.domain.model.HistoricalModel


/**
 * [HistoricalAction] Describe all the actions that can happen in the view.
 * */
sealed interface HistoricalAction {
    /**
     * [OnHistoricalClicked] should be called when the profile button is clicked.
     *
     * */
    data class OnHistoricalClicked(val historicalModel: HistoricalModel) : HistoricalAction

    /**
     * [OnDismissDetailHistorical] should be called when the profile should be dismissed.
     *
     * */
    data object OnDismissDetailHistorical : HistoricalAction
}