package com.spherixlabs.trekscape.core.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.spherixlabs.trekscape.activity.data.db.dao.ActivityDao
import com.spherixlabs.trekscape.activity.data.db.model.ActivityEntity
import com.spherixlabs.trekscape.core.data.db.converter.CoordinatesDataEntityConverter
import com.spherixlabs.trekscape.place.data.db.dao.PlaceDao
import com.spherixlabs.trekscape.place.data.db.model.PlaceEntity

/**Base [TrekScapeDatabase] database class
 * */
@Database(
    entities = [
        ActivityEntity::class,
        PlaceEntity::class,
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    value = [
        CoordinatesDataEntityConverter::class,
    ]
)
abstract class TrekScapeDatabase: RoomDatabase() {
    companion object {
        const val DATABASE_NAME = "trekscape_db"
    }
    abstract val activityDao : ActivityDao
    abstract val placeDao    : PlaceDao
}