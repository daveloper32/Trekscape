package com.spherixlabs.trekscape.historical.presentation.screens.detail_historical

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
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
     * This function receives all the possible actions [DetailAction] from the view and
     * updates the state to reflect the new action.
     *
     * @param action [DetailAction].
     * */
    fun onAction(
        action : DetailAction
    ) {
        when (action) {
            DetailAction.OnShowRecommendationOnMap -> {
                viewModelScope.launch {
                    eventChannel.send(DetailEvent.OnShowRecommendationOnMap)
                }
            }
        }
    }
}