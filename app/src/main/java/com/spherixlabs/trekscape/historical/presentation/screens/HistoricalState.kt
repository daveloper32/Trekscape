package com.spherixlabs.trekscape.historical.presentation.screens

import com.spherixlabs.trekscape.historical.domain.model.HistoricalModel


/**
 * Describe the state [HistoricalState] of the historical screen.
 *
 * @property historicalList List of [HistoricalModel] representing user history.
 * */
data class HistoricalState(
    val historicalList  : List<HistoricalModel> = emptyList(),
)