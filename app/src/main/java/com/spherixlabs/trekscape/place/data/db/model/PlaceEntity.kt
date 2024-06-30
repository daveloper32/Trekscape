package com.spherixlabs.trekscape.place.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.spherixlabs.trekscape.core.utils.constants.Constants.EMPTY_STR
import com.spherixlabs.trekscape.core.utils.constants.Constants.INT_INVALID

/**
 * [PlaceEntity] is the data class that represents a place entity in the database.
 *
 * @property id [Int] The unique identifier of the place.
 * @property name [String] The name of the place.
 * @property description [String] The description of the place.
 * @property imageUrl [String] The URL of the image associated with the place.
 * */
@Entity
data class PlaceEntity(
    @PrimaryKey(autoGenerate = true)
    val id          : Int    = INT_INVALID,
    val name        : String = EMPTY_STR,
    val description : String = EMPTY_STR,
    val imageUrl    : String = EMPTY_STR,
)
