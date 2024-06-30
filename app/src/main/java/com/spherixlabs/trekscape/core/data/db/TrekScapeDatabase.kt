package com.spherixlabs.trekscape.core.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.spherixlabs.trekscape.place.data.db.model.PlaceEntity

/**Base [TrekScapeDatabase] database class
 * */
@Database(
    entities = [
        PlaceEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class TrekScapeDatabase: RoomDatabase() {
    companion object {
        const val DATABASE_NAME = "trekscape_db"
    }
}