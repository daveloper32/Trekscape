package com.spherixlabs.trekscape.historical.presentation.screens.list_history

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.spherixlabs.trekscape.core.data.provider.ResourceProvider
import com.spherixlabs.trekscape.core.domain.storage.UserStorage
import com.spherixlabs.trekscape.historical.domain.model.HistoricalModel
import com.spherixlabs.trekscape.place.domain.model.PlaceData
import com.spherixlabs.trekscape.place.domain.use_cases.GetAndSearchPlacesFromLocalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * The [HistoricalViewModel] is a core or base View Model that is responsible for handling the
 * control of the historical of the application.
 * */
@HiltViewModel
class HistoricalViewModel @Inject constructor(
    private val getAndSearchPlacesFromLocalUseCase : GetAndSearchPlacesFromLocalUseCase,
    private val userStorage                        : UserStorage,
    private val resourceProvider : ResourceProvider
) : ViewModel() {
    /**
     * Private mutable [Channel] that exposes the current events [HistoricalEvent] launched by
     * the view model that should be consumed by the view.
     * */
    private val eventChannel = Channel<HistoricalEvent>()
    /**
     * Public mutable [Channel] as a [Flow] that exposes the current events [HistoricalEvent]
     * launched by the view model that should be consumed by the view.
     * */
    val events = eventChannel.receiveAsFlow()
    /**
     * Private [MutableStateFlow] and Public [StateFlow] that exposes the current state [HistoricalState] of the view model.
     * */
    var state by mutableStateOf(HistoricalState())
        private set

    init { getLocalPlaces() }
    /**
     * Retrieves local places to be displayed on the screen.
     *
     * This function  fetch local places. The retrieved list of places is then stored in the
     * state for display.
     */
    private fun getLocalPlaces(){
        viewModelScope.launch {
            state = state.copy(
                historicalList = getAndSearchPlacesFromLocalUseCase
                    .invoke(coordinatesData = resourceProvider.getCurrentCoordinates())
                    .cachedIn(viewModelScope)
            )
        }
    }
    val historical = getAndSearchPlacesFromLocalUseCase
        .invoke()
        .cachedIn(viewModelScope)

    /**
     * This function receives all the possible actions [HistoricalAction] from the view and
     * updates the state to reflect the new action.
     *
     * @param action [HistoricalAction].
     * */
    fun onAction(
        action : HistoricalAction
    ) {
        when (action) {
            HistoricalAction.OnDismissDetailHistorical -> handleHistoricalItemClicked(null)
            is HistoricalAction.OnHistoricalClicked -> handleHistoricalItemClicked(action.place)
            is HistoricalAction.OnShowRecommendationOnMap -> {
                viewModelScope.launch {
                    eventChannel.send(HistoricalEvent.OnShowRecommendationOnMap(action.placeData))
                }
            }
        }
    }
    /**
     * This function handles the history click action.
     * */
    private fun handleHistoricalItemClicked(
        place : PlaceData?
    ) {
        try {
            state = state.copy(
                isShowingDetailHistorical = place,
            )
        } catch (e: Exception) { Unit }
    }
    /**
     * Returns a list of historical that the user searched.
     *
     * @return List of [HistoricalModel] representing user historical.
     */
    private fun getHistorical() : List<HistoricalModel> = listOf(
        HistoricalModel(
            name          = "beautiful places",
            urlImage      = "https://media.cnn.com/api/v1/images/stellar/prod/190417162012-10-earth-beautiful-places.jpg?q=w_3101,h_1744,x_0,y_0,c_fill",
            missingMeters = "100 km",
            description   = "Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto. Lorem Ipsum ha sido el texto de relleno estándar de las industrias desde el año 1500, cuando un impresor (N. del T. persona"),
        HistoricalModel(
            name          = "Eiffel Tower",
            urlImage      = "https://www.travelandleisure.com/thmb/9xr8CFGR14sLvR4IhLwKV64fEV0=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/TAL-Eiffel-Tower-BESTFRANCE0323-dada0673f8764c099b68d01695ef8057.jpg",
            missingMeters = "100 km",
            description   = "Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto. Lorem Ipsum ha sido el texto de relleno estándar de las industrias desde el año 1500, cuando un impresor (N. del T. persona"),
        HistoricalModel(
            name          = "Taj Mahal",
            urlImage      = "https://worldwildschooling.com/wp-content/uploads/2023/11/Taj-Mahal-India_130860698.jpeg",
            missingMeters = "100 km",
            description   = "Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto. Lorem Ipsum ha sido el texto de relleno estándar de las industrias desde el año 1500, cuando un impresor (N. del T. persona"),
        HistoricalModel(
            name          = "Jumm",
            urlImage      = "https://media.cnn.com/api/v1/images/stellar/prod/190417163847-26-earth-beautiful-places.jpg?q=w_4000,h_2250,x_0,y_0,c_fill",
            missingMeters = "100 km",
            description   = "Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto. Lorem Ipsum ha sido el texto de relleno estándar de las industrias desde el año 1500, cuando un impresor (N. del T. persona"),
    )
}