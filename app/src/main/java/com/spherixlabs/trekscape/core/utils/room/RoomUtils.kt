package com.spherixlabs.trekscape.core.utils.room

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

/**Function to create a Room database based on a context, a [RoomDatabase] class
 * and a name for the database*/
inline fun <reified T : RoomDatabase> createARoomDatabase (
    context: Context,
    name: String
): T {
    return Room
        .databaseBuilder(
            context, // Provide the context
            T::class.java, // Assign the DB class
            name // Assign the name of the database
        )
        .fallbackToDestructiveMigration()
        .build()
}
