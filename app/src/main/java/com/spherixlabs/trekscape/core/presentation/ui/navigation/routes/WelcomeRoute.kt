package com.spherixlabs.trekscape.core.presentation.ui.navigation.routes

import kotlinx.serialization.Serializable

/**
 * [WelcomeRoute] contains all the internal routes for the welcome navigation module.
 * */
@Serializable
object WelcomeRoute {
    /**
     * [NameRequest] is the route for the name request screen.
     * */
    @Serializable
    object NameRequest
    /**
     * [NameRequest] is the route for the preferences request screen.
     * */
    @Serializable
    object PreferencesRequest
}