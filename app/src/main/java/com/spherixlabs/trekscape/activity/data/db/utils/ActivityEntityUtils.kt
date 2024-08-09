package com.spherixlabs.trekscape.activity.data.db.utils

import com.spherixlabs.trekscape.activity.data.db.model.ActivityEntity
import com.spherixlabs.trekscape.activity.domain.model.ActivityData

/**
 * Convert [ActivityEntity] to [ActivityData]
 * */
fun ActivityEntity.toActivityData() = ActivityData(
    id          = this.id,
    placeId     = this.placeId,
    name        = this.name,
    description = this.description,
)

/**
 * Convert [ActivityData] to [ActivityEntity]
 * */
fun ActivityData.toActivityEntity() = ActivityEntity(
    id          = this.id,
    placeId     = this.placeId,
    name        = this.name,
    description = this.description,
)