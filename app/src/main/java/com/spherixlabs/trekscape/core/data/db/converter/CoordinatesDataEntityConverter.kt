package com.spherixlabs.trekscape.core.data.db.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.spherixlabs.trekscape.core.data.db.model.CoordinatesDataEntity

/**
 * [CoordinatesDataEntityConverter] is a class that converts [CoordinatesDataEntity] to and from a
 * JSON string.
 * */
class CoordinatesDataEntityConverter {
    /**
     * This function converts a [CoordinatesDataEntity] to a JSON [String].
     *
     * @param value [CoordinatesDataEntity] The coordinates data entity to convert.
     * @return [String] The JSON string representation of the coordinates data entity.
     */
    @TypeConverter
    fun from(
        value: CoordinatesDataEntity
    ): String {
        val gson = Gson()
        val type = object : TypeToken<CoordinatesDataEntity>() {}.type
        return gson.toJson(value, type)
    }
    /**
     * This function converts a JSON [String] to a [CoordinatesDataEntity].
     *
     * @param value [String] The JSON string to convert.
     * @return [CoordinatesDataEntity] The coordinates data entity represented by the JSON string.
     */
    @TypeConverter
    fun to(
        value: String
    ): CoordinatesDataEntity {
        val gson = Gson()
        val type = object : TypeToken<CoordinatesDataEntity>() {}.type
        return gson.fromJson(value, type)
    }
}