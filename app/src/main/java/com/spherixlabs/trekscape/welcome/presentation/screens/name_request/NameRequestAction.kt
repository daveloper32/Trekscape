package com.spherixlabs.trekscape.welcome.presentation.screens.name_request

/**
 * [NameRequestAction] Describe all the actions that can happen in the view.
 * */
sealed interface NameRequestAction {
    /**
     * [OnNameChanged] should be called when there is a change in the name field.
     *
     * @param name [String] The new name value.
     * */
    data class OnNameChanged(val name: String) : NameRequestAction
    /**
     * [OnNextClicked] should be called when the login button is clicked.
     *
     * */
    data object OnNextClicked : NameRequestAction
}