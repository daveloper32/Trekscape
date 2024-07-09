package com.spherixlabs.trekscape.core.utils.preferences

import android.content.SharedPreferences

/**Extension function to modify the value of a [String] key of a [SharedPreferences] manager*/
fun SharedPreferences.Editor.putStringValue(
    key: String,
    value: String
) {
    with(this) {
        putString(key, value)
        commit()
    }
}

/**Extension function to modify the value of a [Int] key of a [SharedPreferences] manager*/
fun SharedPreferences.Editor.putIntValue(
    key: String,
    value: Int
) {
    with(this) {
        putInt(key, value)
        commit()
    }
}

/**Extension function to modify the value of a [Long] key of a [SharedPreferences] manager*/
fun SharedPreferences.Editor.putLongValue(
    key: String,
    value: Long
) {
    with(this) {
        putLong(key, value)
        commit()
    }
}

/**Extension function to modify the value of a [Boolean] key of a [SharedPreferences] manager*/
fun SharedPreferences.Editor.putBooleanValue(
    key: String,
    value: Boolean
) {
    with(this) {
        putBoolean(key, value)
        commit()
    }
}
/**Extension function to modify the value of a [Set] key of a [SharedPreferences] manager*/
fun SharedPreferences.Editor.putStringSetValue(key: String, ids: Set<String>) {
    with(this) {
        putStringSet(key, ids)
        commit()
    }
}
