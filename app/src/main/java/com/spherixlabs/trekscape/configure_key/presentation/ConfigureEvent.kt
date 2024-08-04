package com.spherixlabs.trekscape.configure_key.presentation


/**
 * [ConfigureEvent] Describe all the events that should be triggered and consumed by the view.
 * */
sealed interface ConfigureEvent {
    /**
     * [GoBack] should be called when the user saves the apikey
     * */
    data object GoBack : ConfigureEvent
}