package com.spherixlabs.trekscape.place.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.spherixlabs.trekscape.core.utils.constants.Constants.DEFAULT_AMOUNT
import com.spherixlabs.trekscape.place.data.db.model.PlaceEntity

/**
 * [PlaceDao] is a DAO interface for the [PlaceEntity] entity.
 * */
@Dao
interface PlaceDao {
    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    /**
     * This function inserts a [PlaceEntity] into the database.
     *
     * @param place [PlaceEntity] The [PlaceEntity] to insert into the database.
     * */
    fun insert(
        place: PlaceEntity
    )
    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    /**
     * This function inserts a list of [PlaceEntity] into the database.
     *
     * @param places [List]<[PlaceEntity]> The list of [PlaceEntity] to insert into the database.
     * */
    fun insertAll(
        places : List<PlaceEntity>
    )
    @Query("SELECT * FROM PlaceEntity LIMIT :limit")
    /**
     * This function gets a list of [PlaceEntity] from the database with a limit of [limit].
     *
     * @param limit [Int] The maximum number of [PlaceEntity] to retrieve.
     * @return [List]<[PlaceEntity]> The list of [PlaceEntity] retrieved from the database.
     * */
    fun getPlaces(
        limit : Int = DEFAULT_AMOUNT,
    ) : List<PlaceEntity>
}