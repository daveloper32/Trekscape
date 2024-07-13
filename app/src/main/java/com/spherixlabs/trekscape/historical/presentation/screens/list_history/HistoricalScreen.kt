package com.spherixlabs.trekscape.historical.presentation.screens.list_history

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.spherixlabs.trekscape.core.presentation.components.TrekScapeSheetDialog
import com.spherixlabs.trekscape.core.presentation.components.handlers.AutoFinishBackPressHandler
import com.spherixlabs.trekscape.core.presentation.ui.theme.TrekScapeTheme
import com.spherixlabs.trekscape.historical.presentation.screens.list_history.components.HeaderHistoricalView
import com.spherixlabs.trekscape.historical.presentation.screens.list_history.components.ItemHistoricalView
import com.spherixlabs.trekscape.historical.presentation.screens.detail_historical.DetailHistoricalScreenRoot

@Composable
fun HistoricalScreenRoot(
    viewModel : HistoricalViewModel = hiltViewModel(),
) {
    HistoricalScreen(
        state    = viewModel.state,
        onAction = viewModel::onAction
    )
}

@Composable
fun HistoricalScreen(
    state   : HistoricalState,
    onAction: (HistoricalAction) -> Unit
) {
    AutoFinishBackPressHandler()
    Box {
        LazyColumn(modifier = Modifier.padding(horizontal = 20.dp)) {
            item { HeaderHistoricalView() }
            itemsIndexed(state.historicalList) { _, historicalModel ->
                ItemHistoricalView( historicalModel  = historicalModel){
                    onAction(HistoricalAction.OnHistoricalClicked(historicalModel))
                }
            }
        }
        TrekScapeSheetDialog(
            isOpen    = state.isShowingDetailHistorical != null,
            showLabel = false,
            expanded  = true,
            onDismiss = { onAction(HistoricalAction.OnDismissDetailHistorical)}) {
            DetailHistoricalScreenRoot(historicalModel = state.isShowingDetailHistorical!!)
        }
    }
}


@Preview
@Composable
private fun HistoricalScreenPreview() {
    TrekScapeTheme {
        HistoricalScreen(
            state    = HistoricalState(),
            onAction = {}
        )
    }
}