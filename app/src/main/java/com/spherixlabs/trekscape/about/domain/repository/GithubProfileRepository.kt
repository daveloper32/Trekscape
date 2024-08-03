package com.spherixlabs.trekscape.about.domain.repository

import com.spherixlabs.trekscape.about.domain.model.DeveloperProfile
import com.spherixlabs.trekscape.core.domain.utils.results.DataError
import com.spherixlabs.trekscape.core.domain.utils.results.Result

/**
 * [GithubProfileRepository] declares all the functions that are required to be implemented in a
 * repository implementation. This functions are related to the developer github profiles.
 * */
interface GithubProfileRepository {
    /**
     * This function should be called if the app developer profiles are not cached in the local.
     *
     * @param userName [String] the username of the developer on Github.
     * @return [Result]<[DeveloperProfile], [DataError.Network]>
     * */
    suspend fun getProfile(
        userName : String,
    ): Result<DeveloperProfile, DataError.Network>
}