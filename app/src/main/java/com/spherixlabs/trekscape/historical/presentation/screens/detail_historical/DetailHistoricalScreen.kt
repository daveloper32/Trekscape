package com.spherixlabs.trekscape.historical.presentation.screens.detail_historical

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.spherixlabs.trekscape.R
import com.spherixlabs.trekscape.core.presentation.components.ObserveAsEvents
import com.spherixlabs.trekscape.core.presentation.components.TrekScapeActionButton
import com.spherixlabs.trekscape.core.presentation.components.TrekScapeWritingTextAnimation
import com.spherixlabs.trekscape.core.presentation.components.handlers.AutoFinishBackPressHandler
import com.spherixlabs.trekscape.core.presentation.ui.theme.TrekScapeTheme
import com.spherixlabs.trekscape.historical.presentation.screens.detail_historical.components.HeaderDetailHistoricalView
import com.spherixlabs.trekscape.historical.presentation.screens.detail_historical.components.ItemActivityView
import com.spherixlabs.trekscape.place.domain.model.PlaceData

@Composable
fun DetailHistoricalScreenRoot(
    place            : PlaceData,
    onShowPlaceOnMap : (PlaceData) -> Unit,
    onDismiss        : () -> Unit,
    viewModel        : DetailViewModel = hiltViewModel(),
) {
    LaunchedEffect(key1 = place) {
        viewModel.onAction(DetailAction.OnDataReceived(place))
    }
    ObserveAsEvents(flow = viewModel.events) { event ->
        when (event) {
            is DetailEvent.OnShowSomePlaceOnMap -> onShowPlaceOnMap(event.place)
            DetailEvent.OnDismiss -> onDismiss()
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
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        item {
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
        }
        item {
            Column(
                modifier = Modifier
                    .padding(
                        vertical = 16.dp,
                        horizontal = 20.dp
                    )
            ) {
                TrekScapeWritingTextAnimation(
                    text       = state.place.name,
                    textStyle  = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold),
                    textColor  = MaterialTheme.colorScheme.onSurface,
                    animationDuration = 500,
                    delayBeforeStart  = 200,
                )
                Text(
                    text  = state.place.description,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                if (state.isLoadingActivities) {
                    Row(
                        modifier  = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                            .padding(
                                top = 20.dp,
                                end = 20.dp
                            ),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .size(20.dp),
                            color = MaterialTheme.colorScheme.primary,
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text  = stringResource(id = R.string.lab_loading_activities),
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
                if (state.activities.isNotEmpty()) {
                    Spacer(modifier = Modifier.size(20.dp))
                    Text(
                        text = stringResource(
                            id = R.string.lab_activities,
                        ),
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.SemiBold,
                    )
                }
            }
        }
        items(
            count = state.activities.size,
            key = { index -> state.activities[index].id }
        ) {
            ItemActivityView(
                activity = state.activities[it],
                number   = it + 1,
                modifier = Modifier
                    .padding(
                        horizontal = 16.dp,
                    )
            )
        }
        item {
            TrekScapeActionButton(
                text      = stringResource(id = R.string.lab_delete),
                isLoading = false,
                enabled   = true,
                modifier  = Modifier.padding(
                    vertical = 40.dp,
                    horizontal = 20.dp,
                ) ,
                onClick   = { onAction(DetailAction.OnDeletePlaceClicked) },
            )
            Spacer(modifier = Modifier.height(20.dp))
        }
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