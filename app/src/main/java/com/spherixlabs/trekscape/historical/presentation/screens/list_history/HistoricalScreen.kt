package com.spherixlabs.trekscape.historical.presentation.screens.list_history

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ManageSearch
import androidx.compose.material.icons.rounded.ManageSearch
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.spherixlabs.trekscape.core.presentation.components.ObserveAsEvents
import com.spherixlabs.trekscape.core.presentation.components.TrekScapeSheetDialog
import com.spherixlabs.trekscape.core.presentation.components.handlers.AutoFinishBackPressHandler
import com.spherixlabs.trekscape.core.presentation.ui.theme.TrekScapeTheme
import com.spherixlabs.trekscape.historical.presentation.screens.detail_historical.DetailHistoricalScreenRoot
import com.spherixlabs.trekscape.historical.presentation.screens.list_history.components.EmptyHistoricalView
import com.spherixlabs.trekscape.historical.presentation.screens.list_history.components.HeaderHistoricalView
import com.spherixlabs.trekscape.historical.presentation.screens.list_history.components.ItemHistoricalView
import com.spherixlabs.trekscape.place.domain.model.PlaceData
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.toList

@Composable
fun HistoricalScreenRoot(
    onShowPlaceOnMap : (PlaceData) -> Unit,
    viewModel        : HistoricalViewModel = hiltViewModel(),
) {
    ObserveAsEvents(flow = viewModel.events) { event ->
        when (event) {
            is HistoricalEvent.OnShowSomePlaceOnMap -> onShowPlaceOnMap(event.place)
        }
    }
    val historicalLazyPagingItems = viewModel.state.historicalList.collectAsLazyPagingItems()
    HistoricalScreen(
        state                     = viewModel.state,
        historicalLazyPagingItems = historicalLazyPagingItems,
        onAction                  = viewModel::onAction,
    )
}

@Composable
fun HistoricalScreen(
    state                     : HistoricalState,
    historicalLazyPagingItems : LazyPagingItems<PlaceData>,
    onAction                  : (HistoricalAction) -> Unit,
) {
    AutoFinishBackPressHandler()
    Box {
        LazyColumn(
            modifier = Modifier.padding(horizontal = 20.dp)
                .safeDrawingPadding()
        ) {
            item { HeaderHistoricalView(state.showOnlyFavorites) {onAction(HistoricalAction.ShowOnlyFavorites(it))  } }
            item {if(historicalLazyPagingItems.itemCount == 0) EmptyHistoricalView()}
            items(
                count       = historicalLazyPagingItems.itemCount,
                key         = historicalLazyPagingItems.itemKey { place -> place.id },
                contentType = historicalLazyPagingItems.itemContentType { "Places" },
            ) { index: Int ->
                historicalLazyPagingItems[index]?.let { place: PlaceData ->
                    ItemHistoricalView(
                        place = place,
                        onFavoriteClicked = {
                            onAction(HistoricalAction.OnSetOrUnsetPlaceAsFavorite(place))
                        }
                    ) {
                        onAction(HistoricalAction.OnHistoricalClicked(place))
                    }
                }
            }
        }
        TrekScapeSheetDialog(
            isOpen    = state.isShowingDetailHistorical != null,
            showLabel = false,
            expanded  = true,
            onDismiss = { onAction(HistoricalAction.OnDismissDetailHistorical) },
        ) {
            DetailHistoricalScreenRoot(
                place = state.isShowingDetailHistorical!!,
                onShowPlaceOnMap = { place ->
                    onAction(HistoricalAction.OnShowSomePlaceOnMapClicked(place))
                },
                onDismiss = {
                    onAction(HistoricalAction.OnDismissDetailHistorical)
                },
            )
        }
    }
}


@Preview
@Composable
private fun HistoricalScreenPreview() {
    TrekScapeTheme {
        HistoricalScreen(
            state    = HistoricalState(),
            onAction = {},
            historicalLazyPagingItems = emptyFlow<PagingData<PlaceData>>().collectAsLazyPagingItems(),
        )
    }
}