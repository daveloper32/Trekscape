package com.spherixlabs.trekscape.about.domain.model

import com.spherixlabs.trekscape.core.utils.constants.Constants.EMPTY_STR

/**
 * [DeveloperProfile] is a data class that represents a developer profile.
 *
 * @property userName [String] the username of the developer on Github.
 * @property avatarUrl [String] the url of the developer avatar.
 * @property profileUrl [String] the url of the developer Github profile.
 * @property name [String] the name of the developer.
 * @property bio [String] the bio of the developer.
 * */
data class DeveloperProfile(
    val userName   : String = EMPTY_STR,
    val avatarUrl  : String = EMPTY_STR,
    val profileUrl : String = EMPTY_STR,
    val name       : String = EMPTY_STR,
    val bio        : String = EMPTY_STR,
)