package com.spherixlabs.trekscape.historical.presentation.screens.detail_historical

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spherixlabs.trekscape.core.domain.utils.results.Result
import com.spherixlabs.trekscape.place.domain.model.PlaceData
import com.spherixlabs.trekscape.place.domain.use_cases.DeletePlaceUseCase
import com.spherixlabs.trekscape.place.domain.use_cases.SetPlaceAsFavoriteUseCase
import com.spherixlabs.trekscape.recommendations.domain.use_cases.GetSomePlaceActivityRecommendationsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * The [DetailViewModel] is a core or base View Model that is responsible for handling the
 * control of the historical of the application.
 * */
@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getSomePlaceActivityRecommendationsUseCase : GetSomePlaceActivityRecommendationsUseCase,
    private val setPlaceAsFavoriteUseCase                  : SetPlaceAsFavoriteUseCase,
    private val deletePlaceUseCase                         : DeletePlaceUseCase,
) : ViewModel() {
    /**
     * Private mutable [Channel] that exposes the current events [DetailEvent] launched by
     * the view model that should be consumed by the view.
     * */
    private val eventChannel = Channel<DetailEvent>()
    /**
     * Public mutable [Channel] as a [Flow] that exposes the current events [DetailEvent]
     * launched by the view model that should be consumed by the view.
     * */
    val events = eventChannel.receiveAsFlow()
    /**
     * Private [MutableStateFlow] and Public [StateFlow] that exposes the current state [DetailState] of the view model.
     * */
    var state by mutableStateOf(DetailState())
        private set
    /**
     * This function receives all the possible actions [DetailAction] from the view and
     * updates the state to reflect the new action.
     *
     * @param action [DetailAction].
     * */
    fun onAction(
        action : DetailAction
    ) {
        when (action) {
            is DetailAction.OnDataReceived -> {
                state = state.copy(
                    place = action.place,
                    activities = emptyList(),
                )
                tryToGetSomeActivitiesOnPlace(place = action.place)
            }
            DetailAction.OnSetOrUnsetPlaceAsFavorite -> handleSetOrUnsetPlaceAsFavorite()
            DetailAction.OnShowPlaceOnMapClicked -> {
                viewModelScope.launch {
                    eventChannel.send(DetailEvent.OnShowSomePlaceOnMap(state.place))
                }
            }
            DetailAction.OnDeletePlaceClicked -> handleDeletePlace()
        }
    }

    /**
     * This function tries to get some activities on a place.
     * */
    private fun tryToGetSomeActivitiesOnPlace(
        place: PlaceData
    ) {
        try {
            viewModelScope.launch {
                state = state.copy(
                    isLoadingActivities = true,
                )
                val result = getSomePlaceActivityRecommendationsUseCase.invoke(
                    place = place,
                )
                when (result) {
                    is Result.Success -> {
                        state = state.copy(
                            activities = result.data,
                            isLoadingActivities = false,
                        )
                    }
                    is Result.Error -> {
                        state = state.copy(
                            activities = emptyList(),
                            isLoadingActivities = false,
                        )
                    }
                }
            }
        } catch (e: Exception) { Unit }
    }

    /**
     * This function handles the set or unset place as favorite action.
     * */
    private fun handleSetOrUnsetPlaceAsFavorite() {
        try {
            viewModelScope.launch {
                setPlaceAsFavoriteUseCase.invoke(
                    id         = state.place.id,
                    isFavorite = !state.place.isFavorite,
                )
                state = state.copy(
                    place = state.place.copy(
                        isFavorite = !state.place.isFavorite,
                    ),
                )
            }
        } catch (e: Exception) { Unit }
    }

    /**
     * This function handles the delete place action.
     * */
    private fun handleDeletePlace() {
        try {
            viewModelScope.launch {
                deletePlaceUseCase.invoke(
                    id = state.place.id,
                )
                eventChannel.send(DetailEvent.OnDismiss)
            }
        } catch (e: Exception) { Unit }
    }
}