package com.spherixlabs.trekscape.about.data.github_profile.utils

import com.spherixlabs.trekscape.about.data.github_profile.model.user.GithubProfileUserResponse
import com.spherixlabs.trekscape.about.domain.model.DeveloperProfile
import com.spherixlabs.trekscape.core.utils.constants.Constants.EMPTY_STR

/**
 * Convert [GithubProfileUserResponse] to [DeveloperProfile]
 * */
fun GithubProfileUserResponse.toDeveloperProfile(): DeveloperProfile = DeveloperProfile(
    userName   = this.userName ?: EMPTY_STR,
    avatarUrl  = this.avatarUrl ?: EMPTY_STR,
    profileUrl = this.profileUrl ?: EMPTY_STR,
    name       = this.name ?: EMPTY_STR,
    bio        = this.bio ?: EMPTY_STR,
)