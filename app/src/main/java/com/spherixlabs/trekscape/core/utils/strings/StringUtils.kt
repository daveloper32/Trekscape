package com.spherixlabs.trekscape.core.utils.strings

import android.util.Patterns

/**
 * [StringUtils] is a utility class that provides methods for easily handling string utilities.
 * */
object StringUtils {
    /**
     * This function checks if some [String] is a valid url.
     *
     * @param value [String] The [String] to check.
     * @return [Boolean] true if is a valid url, false otherwise.
     * */
    fun isValidUrl(
        value : String?
    ): Boolean = !value.isNullOrEmpty() &&
            Patterns.WEB_URL.matcher(value).matches()
}