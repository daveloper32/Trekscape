package com.spherixlabs.trekscape.historical.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.spherixlabs.trekscape.core.presentation.components.handlers.AutoFinishBackPressHandler
import com.spherixlabs.trekscape.core.presentation.ui.theme.TrekScapeTheme
import com.spherixlabs.trekscape.historical.presentation.components.HeaderHistoricalView
import com.spherixlabs.trekscape.historical.presentation.components.ItemHistoricalView

@Composable
fun HistoricalScreenRoot(
    viewModel : HistoricalViewModel = hiltViewModel(),
) {
    HistoricalScreen(state = viewModel.state)
}

@Composable
fun HistoricalScreen(
    state : HistoricalState,
) {
    AutoFinishBackPressHandler()
    Box {
        LazyColumn(modifier = Modifier.padding(horizontal = 20.dp)) {
            item { HeaderHistoricalView() }
            itemsIndexed(state.historicalList) { _, historicalModel ->
                ItemHistoricalView( historicalModel  = historicalModel)
            }
        }
    }
}


@Preview
@Composable
private fun HistoricalScreenPreview() {
    TrekScapeTheme {
        HistoricalScreen(state = HistoricalState())
    }
}