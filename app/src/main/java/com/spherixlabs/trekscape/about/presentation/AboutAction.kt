package com.spherixlabs.trekscape.about.presentation

import com.spherixlabs.trekscape.about.domain.model.DeveloperProfile

/**
 * [AboutAction] Describe all the actions that can happen in the view.
 * */
sealed interface AboutAction {
    /**
     * [OnSomeDeveloperProfileClicked] should be called when some developer profile is clicked.
     *
     * @param developer [DeveloperProfile] the developer profile clicked.
     * */
    data class OnSomeDeveloperProfileClicked(
        val developer : DeveloperProfile
    ): AboutAction
}