package com.spherixlabs.trekscape.activity.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.spherixlabs.trekscape.activity.data.db.model.ActivityEntity

/**
 * [ActivityDao] is a DAO interface for the [ActivityEntity] entity.
 * */
@Dao
interface ActivityDao {
    /**
     * This function inserts a [ActivityEntity] into the database.
     *
     * @param activity [ActivityEntity] The [ActivityEntity] to insert into the database.
     * */
    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    fun insert(
        activity: ActivityEntity
    )
    /**
     * This function inserts a list of [ActivityEntity] into the database.
     *
     * @param activities [List]<[ActivityEntity]> The list of [ActivityEntity] to insert into the database.
     * */
    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    fun insertAll(
        activities : List<ActivityEntity>
    )
    /**
     * This function gets all the [ActivityEntity] that match the [ActivityEntity.placeId].
     *
     * @param placeId [String] The [ActivityEntity.placeId] to match.
     * @return [List]<[ActivityEntity]> The list of [ActivityEntity] retrieved from the database.
     * */
    @Query("""
        SELECT * FROM ActivityEntity 
        WHERE placeId = :placeId
        ORDER BY id DESC
        """
    )
    fun getByPlaceId(
        placeId : String,
    ): List<ActivityEntity>
    /**
     * This function deletes the  a [ActivityEntity] based on its [ActivityEntity.id].
     *
     * @param id [String] The [ActivityEntity.id] of the [ActivityEntity] to delete.
     * */
    @Query("DELETE FROM ActivityEntity WHERE id = :id")
    fun delete(
        id : String,
    )
    /**
     * This function deletes all the [ActivityEntity] based on its [ActivityEntity.placeId].
     *
     * @param placeId [String] The [ActivityEntity.placeId] of the [ActivityEntity]'s to delete.
     * */
    @Query("DELETE FROM ActivityEntity WHERE placeId = :placeId")
    fun deleteByPlaceId(
        placeId : String,
    )
}