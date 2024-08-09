package com.spherixlabs.trekscape.activity.domain.model

import com.spherixlabs.trekscape.core.utils.constants.Constants.EMPTY_STR
import com.spherixlabs.trekscape.core.utils.uuid.UUIDGeneratorUtils

/**
 * [ActivityData] is the domain data class that represents a activity.
 *
 * @property id [String] The unique identifier of the activity.
 * @property placeId [String] The unique identifier of the associated place.
 * @property name [String] The name of the activity.
 * @property description [String] The description of the activity.
 * */
data class ActivityData(
    val id          : String = UUIDGeneratorUtils.generateUUIDWithUTCTimestampAndRandomString(),
    val placeId     : String = EMPTY_STR,
    val name        : String = EMPTY_STR,
    val description : String = EMPTY_STR,
)