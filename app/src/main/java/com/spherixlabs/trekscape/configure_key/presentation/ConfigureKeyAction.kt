package com.spherixlabs.trekscape.configure_key.presentation

/**
 * [ConfigureKeyAction] Describe all the actions that can happen in the view.
 * */
sealed interface ConfigureKeyAction {
    /**
     * [OnApiKeyChanged] should be called when there is a change in the apikey field.
     *
     * @param apikey [String] The new apikey value.
     * */
    data class OnApiKeyChanged(val apikey: String) : ConfigureKeyAction
    /**
     * [SaveApiKey] should be called when the user saves their apikey
     *
     * */
    data object SaveApiKey : ConfigureKeyAction
}