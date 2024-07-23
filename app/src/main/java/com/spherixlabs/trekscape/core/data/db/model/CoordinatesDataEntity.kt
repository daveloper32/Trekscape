package com.spherixlabs.trekscape.core.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.spherixlabs.trekscape.core.utils.constants.Constants.INT_INVALID

/**
 * [CoordinatesDataEntity] is a data class that represents the coordinates of a location as a entity
 * in the database.
 *
 * @property id [Int] The unique identifier of the place.
 * @property latitude [Double] The latitude of the location.
 * @property longitude [Double] The longitude of the location.
 * */
@Entity
data class CoordinatesDataEntity(
    @PrimaryKey(autoGenerate = true)
    val id        : Int    = INT_INVALID,
    val latitude  : Double,
    val longitude : Double,
)