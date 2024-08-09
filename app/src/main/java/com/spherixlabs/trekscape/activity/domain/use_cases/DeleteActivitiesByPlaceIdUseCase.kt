package com.spherixlabs.trekscape.activity.domain.use_cases

import com.spherixlabs.trekscape.activity.domain.model.ActivityData
import com.spherixlabs.trekscape.activity.domain.repository.ActivityRepository
import com.spherixlabs.trekscape.core.domain.utils.results.DataError
import com.spherixlabs.trekscape.core.domain.utils.results.Result
import javax.inject.Inject

/**
 * [DeleteActivitiesByPlaceIdUseCase] represents all the process for deleting a activities related
 * to a place.
 * */
class DeleteActivitiesByPlaceIdUseCase @Inject constructor(
    private val repository : ActivityRepository
) {
    /**
     * This function deletes all the [ActivityData] based on its [ActivityData.placeId].
     *
     * @param id [String] the [ActivityData.placeId].
     * @return [Result]<[Boolean], [DataError.DB]>
     * */
    suspend operator fun invoke(
        id : String,
    ): Result<Boolean, DataError.DB> {
        return repository.deleteByPlaceId(
            placeId = id,
        )
    }
}