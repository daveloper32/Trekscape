package com.spherixlabs.trekscape.core.utils.uuid

import com.spherixlabs.trekscape.core.utils.date.DateUtils.getCurrentUTCTimestamp
import com.spherixlabs.trekscape.core.utils.random.RandomUtils.getRandomString

/**
 * [UUIDGeneratorUtils] is a utility class that provides functions for generating UUIDs.
 * */
object UUIDGeneratorUtils {
    /**Function that generates a UUID concatenating two strings as follows:
     * 1. The current UTC timestamp.
     * 2. A random string of some specified characters.
     *
     * @param stringLength ([Int] type), the length of the random string at the end of the UUID. By
     * default, the value is set up to 12.
     * @return [String]
     * */
    fun generateUUIDWithUTCTimestampAndRandomString(
        stringLength: Int = 12
    ): String = "${getCurrentUTCTimestamp()}${getRandomString(stringLength)}"
}