package com.spherixlabs.trekscape.core.presentation.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

/**
 * [PrimaryColor] is a color palette for the primary color of the app.
 * This includes the different color versions: C900, C800, C700, C600, C500, C400, C300, C200, C100, C50.
 * Where the default color is C500, the light color is C50, and the dark color is C900.
 * */
object PrimaryColor {
    val C900    = Color(0xFF1045a1)
    val C800    = Color(0xFF1764c0)
    val C700    = Color(0xFF1a75d2)
    val C600    = Color(0xFF1f87e5)
    val C500    = Color(0xFF2194f3)
    val C400    = Color(0xFF42a4f5)
    val C300    = Color(0xFF63b4f6)
    val C200    = Color(0xFF90c9f9)
    val C100    = Color(0xFFbbdefb)
    val C50     = Color(0xFFe3f2fd)
    val DEFAULT = C500
    val LIGHT   = C50
    val DARK    = C900
}
/**
 * [SecondaryColor] is a color palette for the secondary color of the app.
 * This includes the different color versions: C900, C800, C700, C600, C500, C400, C300, C200, C100, C50.
 * Where the default color is C900, the light color is C50, and the dark color is C900.
 * */
object SecondaryColor {
    val C900    = Color(0xFFf37f21)
    val C800    = Color(0xFFf8a82e)
    val C700    = Color(0xFFfbbf35)
    val C600    = Color(0xFFfdd73d)
    val C500    = Color(0xFFfbe53d)
    val C400    = Color(0xFFfdeb5a)
    val C300    = Color(0xFFfff079)
    val C200    = Color(0xFFfff49f)
    val C100    = Color(0xFFfff9c5)
    val C50     = Color(0xFFfffde7)
    val DEFAULT = C900
    val LIGHT   = C50
    val DARK    = C900
}
/**
 * [Analogous1Color] is a color palette for the analogous color 1 of the app.
 * This includes the different color versions: C900, C800, C700, C600, C500, C400, C300, C200, C100, C50.
 * Where the default color is C200, the light color is C50, and the dark color is C900.
 * */
object Analogous1Color {
    val C900    = Color(0xFF008267)
    val C800    = Color(0xFF00a189)
    val C700    = Color(0xFF00b39a)
    val C600    = Color(0xFF00c5ae)
    val C500    = Color(0xFF00d5be)
    val C400    = Color(0xFF00dfcd)
    val C300    = Color(0xFF00eadb)
    val C200    = Color(0xFF21f3e9)
    val C100    = Color(0xFF9cf7f1)
    val C50     = Color(0xFFd9fcfb)
    val DEFAULT = C200
    val LIGHT   = C50
    val DARK    = C900
}
/**
 * [Analogous2Color] is a color palette for the analogous color 2 of the app.
 * This includes the different color versions: C900, C800, C700, C600, C500, C400, C300, C200, C100, C50.
 * Where the default color is C600, the light color is C50, and the dark color is C900.
 * */
object Analogous2Color {
    val C900    = Color(0xFF0011e0)
    val C800    = Color(0xFF001fe5)
    val C700    = Color(0xFF0024eb)
    val C600    = Color(0xFF212bf3)
    val C500    = Color(0xFF3a30fa)
    val C400    = Color(0xFF6851fc)
    val C300    = Color(0xFF8a71fc)
    val C200    = Color(0xFFaf9bfc)
    val C100    = Color(0xFFd0c3fc)
    val C50     = Color(0xFFede7fe)
    val DEFAULT = C600
    val LIGHT   = C50
    val DARK    = C900
}
/**
 * [Triadic1Color] is a color palette for the triadic color 1 of the app.
 * This includes the different color versions: C900, C800, C700, C600, C500, C400, C300, C200, C100, C50.
 * Where the default color is C500, the light color is C50, and the dark color is C900.
 * */
object Triadic1Color {
    val C900    = Color(0xFF3000d8)
    val C800    = Color(0xFF5200df)
    val C700    = Color(0xFF630de4)
    val C600    = Color(0xFF741bec)
    val C500    = Color(0xFF8021f3)
    val C400    = Color(0xFF964df6)
    val C300    = Color(0xFFaa71f9)
    val C200    = Color(0xFFc39dfa)
    val C100    = Color(0xFFdbc4fb)
    val C50     = Color(0xFFf2e7fe)
    val DEFAULT = C500
    val LIGHT   = C50
    val DARK    = C900
}
/**
 * [Triadic2Color] is a color palette for the triadic color 2 of the app.
 * This includes the different color versions: C900, C800, C700, C600, C500, C400, C300, C200, C100, C50.
 * Where the default color is C400, the light color is C50, and the dark color is C900.
 * */
object Triadic2Color {
    val C900    = Color(0xFF8d0066)
    val C800    = Color(0xFFb6006f)
    val C700    = Color(0xFFcc0073)
    val C600    = Color(0xFFe3007a)
    val C500    = Color(0xFFf5007e)
    val C400    = Color(0xFFf32194)
    val C300    = Color(0xFFf357aa)
    val C200    = Color(0xFFf48ac3)
    val C100    = Color(0xFFf7b9db)
    val C50     = Color(0xFFfce3f1)
    val DEFAULT = C400
    val LIGHT   = C50
    val DARK    = C900
}
val TextBlack        = Color(0XFF535353)
val DarkGrey         = Color(0xFF282C31)
val BlackTransparent = Color(0x4D000000)

/**
 * Custom Light Color Scheme [ColorScheme] for the app.
 * */
val lightColors = lightColorScheme(
    primary      = PrimaryColor.DEFAULT,
    onPrimary    = Color.White,
    onBackground = TextBlack,
    surface      = Color.White,
    onSurface    = TextBlack,
    secondary    = SecondaryColor.DEFAULT,
    tertiary     = Analogous1Color.DEFAULT,
)

/**
 * Custom Dark Color Scheme [ColorScheme] for the app.
 * */
val darkColors = darkColorScheme(
    primary      = PrimaryColor.DEFAULT,
    onPrimary    = Color.White,
    onBackground = Color.White,
    surface      = DarkGrey,
    onSurface    = Color.White,
    secondary    = SecondaryColor.DEFAULT,
    tertiary     = Analogous1Color.DEFAULT,
)