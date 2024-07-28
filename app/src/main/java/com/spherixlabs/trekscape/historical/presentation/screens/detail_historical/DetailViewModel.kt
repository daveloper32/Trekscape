package com.spherixlabs.trekscape.historical.presentation.screens.detail_historical

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
class DetailViewModel @Inject constructor() : ViewModel() {
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
                    place = action.place
                )
            }
            DetailAction.OnShowPlaceOnMapClicked -> {
                viewModelScope.launch {
                    eventChannel.send(DetailEvent.OnShowSomePlaceOnMap(state.place))
                }
            }
        }
    }
}