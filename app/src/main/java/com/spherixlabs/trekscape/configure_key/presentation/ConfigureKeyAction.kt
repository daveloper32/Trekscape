package com.spherixlabs.trekscape.configure_key.presentation

/**
 * [ConfigureKeyAction] Describe all the actions that can happen in the view.
 * */
sealed interface ConfigureKeyAction {
    /**
     * [OnScreenStarted] should be called when the screen is started.
     *
     * */
    data object OnScreenStarted : ConfigureKeyAction
    /**
     * [OnApiKeyChanged] should be called when there is a change in the apikey field.
     *
     * @param apikey [String] The new apikey value.
     * */
    data class OnApiKeyChanged(val apikey: String) : ConfigureKeyAction
    /**
     * [OnValidate] should be called when the user typed their apikey and wants to validate it
     *
     * */
    data object OnValidate : ConfigureKeyAction
    /**
     * [OnBack] should be called when the user wants to type the api key again
     *
     * */
    data object OnBack : ConfigureKeyAction
    /**
     * [OnSave] should be called when the user typed in a valid apikey and wants to save it
     *
     * */
    data object OnSave : ConfigureKeyAction
}