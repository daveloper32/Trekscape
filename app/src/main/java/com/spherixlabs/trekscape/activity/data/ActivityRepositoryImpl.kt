package com.spherixlabs.trekscape.activity.data

import com.spherixlabs.trekscape.activity.data.db.dao.ActivityDao
import com.spherixlabs.trekscape.activity.data.db.model.ActivityEntity
import com.spherixlabs.trekscape.activity.data.db.utils.toActivityData
import com.spherixlabs.trekscape.activity.data.db.utils.toActivityEntity
import com.spherixlabs.trekscape.activity.domain.model.ActivityData
import com.spherixlabs.trekscape.activity.domain.repository.ActivityRepository
import com.spherixlabs.trekscape.core.domain.utils.results.DataError
import com.spherixlabs.trekscape.core.domain.utils.results.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * [ActivityRepositoryImpl] is a implementation of [ActivityRepository].
 * */
class ActivityRepositoryImpl @Inject constructor(
    private val dao : ActivityDao,
): ActivityRepository {

    override suspend fun add(
        activities : List<ActivityData>,
    ): Result<Boolean, DataError.DB> {
        return try {
            withContext(Dispatchers.IO) {
                dao.insertAll(
                    activities = activities.map {
                        it.toActivityEntity()
                    }
                )
                Result.Success(true)
            }
        } catch (e: Exception) {
            Result.Error(DataError.DB.UNKNOWN)
        }
    }

    override suspend fun getByPlaceId(
        placeId : String
    ): Result<List<ActivityData>, DataError.DB> {
        return try {
            withContext(Dispatchers.IO) {
                val data: List<ActivityEntity> = dao
                    .getByPlaceId(placeId = placeId)
                Result.Success(
                    data.map {
                        it.toActivityData()
                    }
                )
            }
        } catch (e: Exception) {
            Result.Error(DataError.DB.UNKNOWN)
        }
    }

    override suspend fun deleteById(
        id : String
    ): Result<Boolean, DataError.DB> {
        return try {
            withContext(Dispatchers.IO) {
                dao.delete(
                    id = id,
                )
                Result.Success(
                    true
                )
            }
        } catch (e: Exception) {
            Result.Error(DataError.DB.UNKNOWN)
        }
    }

    override suspend fun deleteByPlaceId(
        placeId : String
    ): Result<Boolean, DataError.DB> {
        return try {
            withContext(Dispatchers.IO) {
                dao.deleteByPlaceId(
                    placeId = placeId,
                )
                Result.Success(
                    true
                )
            }
        } catch (e: Exception) {
            Result.Error(DataError.DB.UNKNOWN)
        }
    }
}