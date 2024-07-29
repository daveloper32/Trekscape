package com.spherixlabs.trekscape.historical.presentation.screens.detail_historical

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.spherixlabs.trekscape.core.presentation.components.ObserveAsEvents
import com.spherixlabs.trekscape.core.presentation.components.handlers.AutoFinishBackPressHandler
import com.spherixlabs.trekscape.core.presentation.ui.theme.TrekScapeTheme
import com.spherixlabs.trekscape.historical.presentation.screens.detail_historical.components.BodyDetailHistoricalView
import com.spherixlabs.trekscape.historical.presentation.screens.detail_historical.components.HeaderDetailHistoricalView
import com.spherixlabs.trekscape.place.domain.model.PlaceData

@Composable
fun DetailHistoricalScreenRoot(
    place            : PlaceData,
    onShowPlaceOnMap : (PlaceData) -> Unit,
    viewModel        : DetailViewModel = hiltViewModel(),
) {
    LaunchedEffect(key1 = place) {
        viewModel.onAction(DetailAction.OnDataReceived(place))
    }
    ObserveAsEvents(flow = viewModel.events) { event ->
        when (event) {
            is DetailEvent.OnShowSomePlaceOnMap -> onShowPlaceOnMap(event.place)
        }
    }
    DetailHistoricalScreen(
        state = viewModel.state,
        onAction = viewModel ::onAction,
    )
}

@Composable
fun DetailHistoricalScreen(
    state    : DetailState,
    onAction : (DetailAction) -> Unit
) {
    AutoFinishBackPressHandler()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        HeaderDetailHistoricalView(
            urlImage = state.place.imageUrl,
            missingMeters = state.place.missingMeters.ifEmpty { "-" },
            isFavorite = state.place.isFavorite,
            onShowInMapClicked = {
                onAction(DetailAction.OnShowPlaceOnMapClicked)
            },
            onFavoriteClicked = {
                onAction(DetailAction.OnSetOrUnsetPlaceAsFavorite)
            }
        )
        BodyDetailHistoricalView(
            name = state.place.name,
            description = state.place.description
        )
    }
}

@Preview
@Composable
private fun HistoricalScreenPreview() {
    TrekScapeTheme {
        DetailHistoricalScreen(
           state = DetailState(
               place =  PlaceData(
                   name     = "beautiful places",
                   imageUrl = "https://media.cnn.com/api/v1/images/stellar/prod/190417162012-10-earth-beautiful-places.jpg?q=w_3101,h_1744,x_0,y_0,c_fill",
                   missingMeters = "100 km",
                   description = "Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto. Lorem Ipsum ha sido el texto de relleno estándar de las industrias desde el año 1500, cuando un impresor (N. del T. persona"
               )
           )
        ) { }
    }
}