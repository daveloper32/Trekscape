package com.spherixlabs.trekscape.activity.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.spherixlabs.trekscape.core.utils.constants.Constants.EMPTY_STR
import com.spherixlabs.trekscape.core.utils.uuid.UUIDGeneratorUtils

/**
 * [ActivityEntity] is the data class that represents a activity entity in the database.
 *
 * @property id [String] The unique identifier of the activity.
 * @property placeId [String] The unique identifier of the associated place.
 * @property name [String] The name of the activity.
 * @property description [String] The description of the activity.
 * */
@Entity
data class ActivityEntity(
    @PrimaryKey()
    val id          : String = UUIDGeneratorUtils.generateUUIDWithUTCTimestampAndRandomString(),
    val placeId     : String = EMPTY_STR,
    val name        : String = EMPTY_STR,
    val description : String = EMPTY_STR,
)