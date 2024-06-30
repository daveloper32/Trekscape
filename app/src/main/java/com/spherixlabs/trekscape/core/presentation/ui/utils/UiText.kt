package com.spherixlabs.trekscape.core.presentation.ui.utils

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

/**
 * [UiText] is a sealed interface that represents different types of string resources.
 * */
sealed interface UiText {
    /**
     * [DynamicString] is a string that is not a resource and can be used directly to add a [String].
     *
     * @property value [String] - The string value.
     * @return [UiText]
     * */
    data class DynamicString(
        val value : String
    ) : UiText
    /**
     * [StringResource] is a string resource that can be used to add a [String] with arguments.
     *
     * @property resId [Int] - The resource ID of the string. It should be a valid resource ID [StringRes].
     * @property args [Array]<[Any]> - The arguments for the string.
     * @return [UiText]
     * */
    class StringResource(
        @StringRes val resId : Int,
        val args             : Array<Any> = arrayOf(),
    ) : UiText

    /**
     * [Composable] helper function that converts the [UiText] to a [String] based on the current
     * [UiText] type. Can be used into a [Composable] context.
     *
     * @return [String]
     * */
    @Composable
    fun asString(): String {
        return when (this) {
            is DynamicString -> value
            is StringResource -> stringResource(id = resId, *args)
        }
    }

    /**
     * Helper function that converts the [UiText] to a [String] based on the current
     * [UiText] type and [Context]. Can be used in any context.
     *
     * @param context [Context] - The context used for string resource lookup.
     * @return [String]
     * */
    fun asString(
        context: Context,
    ): String {
        return when (this) {
            is DynamicString -> value
            is StringResource -> context.getString(resId, *args)
        }
    }
}