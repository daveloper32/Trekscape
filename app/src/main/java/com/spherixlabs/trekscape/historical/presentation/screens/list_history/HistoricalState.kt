package com.spherixlabs.trekscape.historical.presentation.screens.list_history

import androidx.paging.PagingData
import com.spherixlabs.trekscape.place.domain.model.PlaceData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow


/**
 * Describe the state [HistoricalState] of the historical screen.
 *
 * @property historicalList List of [PlaceData] representing user history.
 * @property isShowingDetailHistorical  Indicates whether the detail historical screen should be visible or not.
 * */
data class HistoricalState(
    val historicalList             : Flow<PagingData<PlaceData>> = emptyFlow(),
    val isShowingDetailHistorical  : PlaceData?  = null
)