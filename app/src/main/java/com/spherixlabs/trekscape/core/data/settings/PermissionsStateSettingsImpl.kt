package com.spherixlabs.trekscape.core.data.settings

import android.content.Context
import android.content.SharedPreferences
import com.spherixlabs.trekscape.core.domain.storage.PermissionsStateStorage
import com.spherixlabs.trekscape.core.utils.preferences.putBooleanValue
import javax.inject.Inject

/**
 * [PermissionsStateSettingsImpl] is an implementation of [PermissionsStateStorage] for handle the settings data on
 * the general app.
 * */
class PermissionsStateSettingsImpl @Inject constructor(
    context: Context,
): PermissionsStateStorage {
    // Init/Get the shared preferences on the PREFERENCES_NAME
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREFERENCES_NAME, 0)
    // Init the shared preferences editor
    private val preferencesEditor: SharedPreferences.Editor =
        sharedPreferences.edit()
    override var isCoarseLocationRationaleShown: Boolean
        get() = sharedPreferences.getBoolean(Keys.COARSE_LOCATION_RATIONALE_SHOWN.name, false)
        set(value) = preferencesEditor.putBooleanValue(Keys.COARSE_LOCATION_RATIONALE_SHOWN.name, value)

    override var isFineLocationRationaleShown: Boolean
        get() = sharedPreferences.getBoolean(Keys.FINE_LOCATION_RATIONALE_SHOWN.name, false)
        set(value) = preferencesEditor.putBooleanValue(Keys.FINE_LOCATION_RATIONALE_SHOWN.name, value)

    override var isCoarseLocationPermanentlyDeclined: Boolean
        get() = sharedPreferences.getBoolean(Keys.COARSE_LOCATION_IS_PERMANENTLY_DECLINED.name, false)
        set(value) = preferencesEditor.putBooleanValue(Keys.COARSE_LOCATION_IS_PERMANENTLY_DECLINED.name, value)

    override var isFineLocationPermanentlyDeclined: Boolean
        get() = sharedPreferences.getBoolean(Keys.FINE_LOCATION_IS_PERMANENTLY_DECLINED.name, false)
        set(value) = preferencesEditor.putBooleanValue(Keys.FINE_LOCATION_IS_PERMANENTLY_DECLINED.name, value)

    companion object {
        private const val PREFERENCES_NAME = "permissions_state_settings"
        /**
         * [Keys] specify all the keys used in [PermissionsStateSettingsImpl]
         * */
        enum class Keys {
            COARSE_LOCATION_RATIONALE_SHOWN,
            FINE_LOCATION_RATIONALE_SHOWN,
            COARSE_LOCATION_IS_PERMANENTLY_DECLINED,
            FINE_LOCATION_IS_PERMANENTLY_DECLINED,;
            val key get() = this.name
        }
    }
}