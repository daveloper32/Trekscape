package com.spherixlabs.trekscape.core.utils.intent

import android.content.Context
import android.content.Intent
import android.net.Uri

/**
 * [IntentUtils] is a utility class that provides methods for easily creating and launching intents.
 * */
object IntentUtils {
    /**
     * Navigates to the application details settings screen.
     *
     * @param context [Context] The application context.
     * */
    fun goToAppDetailsSettings(
        context : Context
    ) {
        try {
            // Creating the intent object
            val i: Intent = Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            i.data = Uri.parse("package:${context.packageName}")
            // Start the intent
            context.startActivity(i)
        } catch (e: Exception) { Unit }
    }
}