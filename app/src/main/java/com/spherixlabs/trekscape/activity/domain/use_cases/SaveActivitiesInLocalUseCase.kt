package com.spherixlabs.trekscape.activity.domain.use_cases

import com.spherixlabs.trekscape.activity.domain.model.ActivityData
import com.spherixlabs.trekscape.activity.domain.repository.ActivityRepository
import com.spherixlabs.trekscape.core.domain.utils.results.DataError
import com.spherixlabs.trekscape.core.domain.utils.results.Result
import javax.inject.Inject

/**
 * [SaveActivitiesInLocalUseCase] represents all the process for saving activities in local storage.
 * */
class SaveActivitiesInLocalUseCase @Inject constructor(
    private val repository : ActivityRepository,
) {
    /**
     * This function saves a list of activities in local storage.
     *
     * @param activities [List]<[ActivityData]> The list of activities to save.
     * @return [Result]<[Boolean], [DataError.DB]>
     * */
    suspend operator fun invoke(
        activities : List<ActivityData>
    ): Result<Boolean, DataError.DB> {
        return repository.add(activities)
    }
}