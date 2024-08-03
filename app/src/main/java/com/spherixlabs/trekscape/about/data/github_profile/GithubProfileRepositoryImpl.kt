package com.spherixlabs.trekscape.about.data.github_profile

import com.spherixlabs.trekscape.about.data.github_profile.model.user.GithubProfileUserResponse
import com.spherixlabs.trekscape.about.data.github_profile.utils.toDeveloperProfile
import com.spherixlabs.trekscape.about.domain.model.DeveloperProfile
import com.spherixlabs.trekscape.about.domain.repository.GithubProfileRepository
import com.spherixlabs.trekscape.core.domain.utils.results.DataError
import com.spherixlabs.trekscape.core.domain.utils.results.Result
import com.spherixlabs.trekscape.core.domain.utils.results.map
import com.spherixlabs.trekscape.core.utils.http.get
import io.ktor.client.HttpClient
import javax.inject.Inject

/**
 * [GithubProfileRepositoryImpl] is a implementation of [GithubProfileRepository].
 * */
class GithubProfileRepositoryImpl @Inject constructor(
    private val httpClient : HttpClient,
) : GithubProfileRepository {
    override suspend fun getProfile(
        userName : String
    ): Result<DeveloperProfile, DataError.Network> {
        val result = httpClient.get<GithubProfileUserResponse>(
            route = "https://api.github.com/users/$userName",
        )
        return result.map { response: GithubProfileUserResponse ->
            response.toDeveloperProfile()
        }
    }
}