package com.spherixlabs.trekscape.about.data.github_profile.model.user

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * [GithubProfileUserResponse] is a data class that represents a Github user response.
 *
 * @property userName [String] the username of the developer on Github.
 * @property avatarUrl [String] the url of the developer avatar.
 * @property profileUrl [String] the url of the developer Github profile.
 * @property name [String] the name of the developer.
 * @property bio [String] the bio of the developer.
 * */
@Serializable
data class GithubProfileUserResponse(
    @SerialName("login")      val userName   : String? = null,
    @SerialName("avatar_url") val avatarUrl  : String? = null,
    @SerialName("html_url")   val profileUrl : String? = null,
    @SerialName("name")       val name       : String? = null,
    @SerialName("bio")        val bio        : String? = null,
)