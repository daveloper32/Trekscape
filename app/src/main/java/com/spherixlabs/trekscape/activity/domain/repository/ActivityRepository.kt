package com.spherixlabs.trekscape.activity.domain.repository

import com.spherixlabs.trekscape.activity.domain.model.ActivityData
import com.spherixlabs.trekscape.core.domain.utils.results.DataError
import com.spherixlabs.trekscape.core.domain.utils.results.Result

interface ActivityRepository {
    /**
     * This function saves a list of activities from the local storage.
     *
     * @param activities [List]<[ActivityData]> The list of activities to be saved.
     * @return [Result]<[Boolean], [DataError.DB]>
     * */
    suspend fun add(
        activities : List<ActivityData>,
    ): Result<Boolean, DataError.DB>
    /**
     * This function gets a list of activities related to the input PlaceId from the local storage.
     *
     * @param placeId [String] the [ActivityData.placeId].
     * @return [Result]<[List]<[ActivityData]>, [DataError.DB]>
     * */
    suspend fun getByPlaceId(
        placeId : String,
    ): Result<List<ActivityData>, DataError.DB>
    /**
     * This function deletes a [ActivityData] based on its [ActivityData.id].
     *
     * @param id [String] the [ActivityData.id].
     * @return [Result]<[Boolean], [DataError.DB]>
     * */
    suspend fun deleteById(
        id : String,
    ): Result<Boolean, DataError.DB>
    /**
     * This function deletes a [ActivityData] based on its [ActivityData.placeId].
     *
     * @param placeId [String] the [ActivityData.placeId].
     * @return [Result]<[Boolean], [DataError.DB]>
     * */
    suspend fun deleteByPlaceId(
        placeId : String,
    ): Result<Boolean, DataError.DB>
}