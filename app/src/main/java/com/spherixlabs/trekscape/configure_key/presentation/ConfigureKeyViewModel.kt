package com.spherixlabs.trekscape.configure_key.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spherixlabs.trekscape.core.domain.storage.UserStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * The [ConfigureKeyViewModel] is a core or base View Model that is responsible for handling the
 * control of the name request of the application.
 * */
@HiltViewModel
class ConfigureKeyViewModel @Inject constructor(
    private val userStorage : UserStorage,
) : ViewModel() {

    /**
     * Private [MutableStateFlow] and Public [StateFlow] that exposes the current state [ConfigureKeyState] of the view model.
     * */
    var state by mutableStateOf(ConfigureKeyState())
        private set
    /**
     * Private mutable [Channel] that exposes the current events [ConfigureEvent] launched by
     * the view model that should be consumed by the view.
     * */
    private val eventChannel = Channel<ConfigureEvent>()
    /**
     * Public mutable [Channel] as a [Flow] that exposes the current events [ConfigureEvent]
     * launched by the view model that should be consumed by the view.
     * */
    val events = eventChannel.receiveAsFlow()
    init {
        initConfiguration()
    }
    /**
     * This function initializes the state
     * */
    private fun initConfiguration(){
        try {
            val apiKey = userStorage.apiKey
            state = state.copy(
                apiKey     = apiKey,
                enableSave = apiKey.isNotEmpty()
            )
        } catch (e: Exception) { Unit }
    }
    /**
     * This function receives all the possible actions [ConfigureKeyAction] from the view and
     * updates the state to reflect the new action.
     *
     * @param action [ConfigureKeyAction].
     * */
    fun onAction(
        action : ConfigureKeyAction
    ) {
        when (action) {
            is ConfigureKeyAction.OnApiKeyChanged ->  {
                state = state.copy(
                    apiKey    = action.apikey,
                    enableSave= action.apikey.isNotEmpty(),
                )
            }

            ConfigureKeyAction.SaveApiKey -> {
                viewModelScope.launch {
                    userStorage.apiKey = state.apiKey
                    eventChannel.send(ConfigureEvent.GoBack)
                }
            }
        }
    }
}