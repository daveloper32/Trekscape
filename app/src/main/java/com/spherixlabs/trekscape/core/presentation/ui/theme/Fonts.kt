package com.spherixlabs.trekscape.core.presentation.ui.theme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.spherixlabs.trekscape.R

/**
 * Custom Poppins font [FontFamily] for the application.
 *
 * It includes the following font variants:
 * - Black.
 * - Black Italic.
 * - Bold.
 * - Bold Italic.
 * - ExtraBold.
 * - ExtraBold Italic.
 * - ExtraLight.
 * - ExtraLight Italic.
 * - Light.
 * - Light Italic.
 * - Medium.
 * - Medium Italic.
 * - SemiBold.
 * - SemiBold Italic.
 * - Thin.
 * - Thin Italic.
 * - Normal.
 * - Normal Italic.
 * */
val Poppins = FontFamily(
    Font(
        resId  = R.font.poppins_black,
        weight = FontWeight.Black,
        style  = FontStyle.Normal,
    ),
    Font(
        resId  = R.font.poppins_black_italic,
        weight = FontWeight.Black,
        style  = FontStyle.Italic,
    ),
    Font(
        resId  = R.font.poppins_bold,
        weight = FontWeight.Bold,
        style  = FontStyle.Normal,
    ),
    Font(
        resId  = R.font.poppins_bold_italic,
        weight = FontWeight.Bold,
        style  = FontStyle.Italic,
    ),
    Font(
        resId  = R.font.poppins_extrabold,
        weight = FontWeight.ExtraBold,
        style  = FontStyle.Normal,
    ),
    Font(
        resId  = R.font.poppins_extrabold_italic,
        weight = FontWeight.ExtraBold,
        style  = FontStyle.Italic,
    ),
    Font(
        resId  = R.font.poppins_extralight,
        weight = FontWeight.ExtraLight,
        style  = FontStyle.Normal,
    ),
    Font(
        resId  = R.font.poppins_extralight_italic,
        weight = FontWeight.ExtraLight,
        style  = FontStyle.Italic,
    ),
    Font(
        resId  = R.font.poppins_light,
        weight = FontWeight.Light,
        style  = FontStyle.Normal,
    ),
    Font(
        resId  = R.font.poppins_light_italic,
        weight = FontWeight.Light,
        style  = FontStyle.Italic,
    ),
    Font(
        resId  = R.font.poppins_medium,
        weight = FontWeight.Medium,
        style  = FontStyle.Normal,
    ),
    Font(
        resId  = R.font.poppins_medium_italic,
        weight = FontWeight.Medium,
        style  = FontStyle.Italic,
    ),
    Font(
        resId  = R.font.poppins_semibold,
        weight = FontWeight.SemiBold,
        style  = FontStyle.Normal,
    ),
    Font(
        resId  = R.font.poppins_semibold_italic,
        weight = FontWeight.SemiBold,
        style  = FontStyle.Italic,
    ),
    Font(
        resId  = R.font.poppins_thin,
        weight = FontWeight.Thin,
        style  = FontStyle.Normal,
    ),
    Font(
        resId  = R.font.poppins_thin_italic,
        weight = FontWeight.Thin,
        style  = FontStyle.Italic,
    ),
    Font(
        resId  = R.font.poppins_regular,
        weight = FontWeight.Normal,
        style  = FontStyle.Normal,
    ),
    Font(
        resId  = R.font.poppins_italic,
        weight = FontWeight.Normal,
        style  = FontStyle.Italic,
    ),
)