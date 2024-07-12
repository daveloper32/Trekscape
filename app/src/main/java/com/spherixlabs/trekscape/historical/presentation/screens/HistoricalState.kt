package com.spherixlabs.trekscape.historical.presentation.screens

import com.spherixlabs.trekscape.historical.domain.model.HistoricalModel


/**
 * Describe the state [HistoricalState] of the historical screen.
 *
 * @property historicalList List of [HistoricalModel] representing user history.
 * @property isShowingDetailHistorical  Indicates whether the detail historical screen should be visible or not.
 * */
data class HistoricalState(
    val historicalList             : List<HistoricalModel> = emptyList(),
    val isShowingDetailHistorical  : HistoricalModel?  = null
)