package com.spherixlabs.trekscape.about.presentation

import com.spherixlabs.trekscape.about.domain.model.DeveloperProfile

/**
 * Describe the state [AboutState] of the home screen.
 *
 * @param developerProfiles [List]<[DeveloperProfile]> The list of developer profiles.
 * @param isLoadingDeveloperProfiles [Boolean] Whether the developer profiles are being loaded.
 * */
data class AboutState(
    val developerProfiles          : List<DeveloperProfile> = emptyList(),
    val isLoadingDeveloperProfiles : Boolean = false,
)