package com.spherixlabs.trekscape.about.domain.use_cases

import com.spherixlabs.trekscape.about.domain.model.DeveloperProfile
import com.spherixlabs.trekscape.about.domain.repository.GithubProfileRepository
import com.spherixlabs.trekscape.core.data.network.utils.NetworkProvider
import com.spherixlabs.trekscape.core.domain.utils.results.DataError
import com.spherixlabs.trekscape.core.domain.utils.results.Result
import javax.inject.Inject

/**
 * [GetDeveloperProfilesUseCase] represents all the process for getting the developer profiles.
 * */
class GetDeveloperProfilesUseCase @Inject constructor(
    private val networkProvider : NetworkProvider,
    private val repository      : GithubProfileRepository,
) {
    /**
     * This function gets the developer profiles.
     *
     * @return [Result]<[List]<[DeveloperProfile]>, [DataError.Network]>
     * */
    suspend operator fun invoke(
    ): Result<List<DeveloperProfile>, DataError.Network> {
        if (!networkProvider.isConnected()) {
            return Result.Error(DataError.Network.NOT_INTERNET)
        }
        val profiles = mutableListOf<DeveloperProfile>()
        DEVELOPER_USER_NAMES.forEach { userName ->
            val result = repository.getProfile(userName)
            if (result is Result.Success) {
                profiles.add(result.data)
            }
        }
        if (
            profiles.isEmpty() &&
            profiles.size != DEVELOPER_USER_NAMES.size
        ) {
            return Result.Error(DataError.Network.NOT_FOUND)
        }
        return Result.Success(
            profiles.sortedBy { it.userName }
        )
    }
    companion object {
        private val DEVELOPER_USER_NAMES = listOf(
            "daveloper32",
            "estarly07"
        )
    }
}