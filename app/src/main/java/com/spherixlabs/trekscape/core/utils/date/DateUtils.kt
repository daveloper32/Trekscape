package com.spherixlabs.trekscape.core.utils.date

import java.util.Calendar
import java.util.TimeZone

/**
 * [DateUtils] is a utility class that provides methods for working with dates and times.
 * */
object DateUtils {
    /**
     * This function returns the current UTC timestamp in milliseconds from the current device.
     *
     * @return [Long] the current UTC timestamp in milliseconds
     * */
    fun getCurrentUTCTimestamp(): Long {
        val currentTimeMillis = System.currentTimeMillis()
        val timeZone = TimeZone.getTimeZone("UTC")
        val calendar = Calendar.getInstance(timeZone)
        calendar.timeInMillis = currentTimeMillis
        return calendar.timeInMillis / 1000
    }
}