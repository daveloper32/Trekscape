package com.spherixlabs.trekscape.welcome.presentation.screens.name_request

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * The [NameRequestViewModel] is a core or base View Model that is responsible for handling the
 * control of the name request of the application.
 * */
class NameRequestViewModel @Inject constructor(

) : ViewModel() {

    /**
     * Private [MutableStateFlow] and Public [StateFlow] that exposes the current state [NameRequestState] of the view model.
     * */
    var state by mutableStateOf(NameRequestState())
        private set

    /**
     * Private mutable [Channel] that exposes the current events [NameRequestEvent] launched by
     * the view model that should be consumed by the view.
     * */
    private val eventChannel = Channel<NameRequestEvent>()
    /**
     * Public mutable [Channel] as a [Flow] that exposes the current events [NameRequestEvent]
     * launched by the view model that should be consumed by the view.
     * */
    val events = eventChannel.receiveAsFlow()

    /**
     * This function receives all the possible actions [NameRequestAction] from the view and
     * updates the state to reflect the new action.
     *
     * @param action [NameRequestAction].
     * */
    fun onAction(
        action : NameRequestAction
    ) {
        when (action) {
            is NameRequestAction.OnNameChanged -> {
                state = state.copy(
                    name = action.name,
                    canGoNext = action.name.isNotEmpty(),
                )
            }
            is NameRequestAction.OnNextClicked -> {
                viewModelScope.launch {
                    eventChannel.send(
                        NameRequestEvent.NavigateToSetupPreferences
                    )
                }
            }
        }
    }
}