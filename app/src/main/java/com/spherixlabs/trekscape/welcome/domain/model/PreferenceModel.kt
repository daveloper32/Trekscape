package com.spherixlabs.trekscape.welcome.domain.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Air
import androidx.compose.material.icons.rounded.BeachAccess
import androidx.compose.material.icons.rounded.BubbleChart
import androidx.compose.material.icons.rounded.Castle
import androidx.compose.material.icons.rounded.Event
import androidx.compose.material.icons.rounded.Festival
import androidx.compose.material.icons.rounded.Forest
import androidx.compose.material.icons.rounded.FormatPaint
import androidx.compose.material.icons.rounded.Hiking
import androidx.compose.material.icons.rounded.Hotel
import androidx.compose.material.icons.rounded.HotelClass
import androidx.compose.material.icons.rounded.Landscape
import androidx.compose.material.icons.rounded.LocationCity
import androidx.compose.material.icons.rounded.Museum
import androidx.compose.material.icons.rounded.Nature
import androidx.compose.material.icons.rounded.NaturePeople
import androidx.compose.material.icons.rounded.PhotoCamera
import androidx.compose.material.icons.rounded.Place
import androidx.compose.material.icons.rounded.Spa
import androidx.compose.material.icons.rounded.TempleBuddhist
import androidx.compose.material.icons.rounded.WbSunny
import androidx.compose.ui.graphics.vector.ImageVector
import com.spherixlabs.trekscape.R
import com.spherixlabs.trekscape.core.presentation.ui.utils.UiText

/**
 * Data class representing a user-selectable preference.
 *
 * @property id  [String] The identifier of the preference, typically the name of the associated enum class.
 * @property title [UiText] The title or display name of the preference.
 * @property icon [ImageVector] The icon associated with the preference, represented as an ImageVector.
 */
data class PreferenceModel(
    val id   : String,
    val title: UiText,
    val icon : ImageVector
) {
    /**
     * Checks if the preference is associated with the [NatureAdventure] category.
     *
     * @return [Boolean] True if the preference is [NatureAdventure], false otherwise.
     * */
    fun isNatureAdventure() : Boolean {
        return NatureAdventure.Companion.Types.entries.find { it.name == id } != null
    }
    /**
     * Checks if the preference is associated with the [CultureHistory] category.
     *
     * @return [Boolean] True if the preference is [CultureHistory], false otherwise.
     * */
    fun isCultureHistory() : Boolean {
        return CultureHistory.Companion.Types.entries.find { it.name == id } != null
    }
    /**
     * Checks if the preference is associated with the [Relaxation] category.
     *
     * @return [Boolean] True if the preference is [Relaxation], false otherwise.
     * */
    fun isRelaxation() : Boolean {
        return Relaxation.Companion.Types.entries.find { it.name == id } != null
    }
}
/**
 * Extracts a set of IDs from a list of PreferenceModel objects.
 * @return A set of strings representing the IDs extracted from the list.
 */
fun List<PreferenceModel>.extractIds(): Set<String> = this.map { it.id }.toSet()

class NatureAdventure  {
    companion object {
        enum class Types(val title: UiText,val icon: ImageVector) {
            MOUNTAINS(
                title = UiText.StringResource(R.string.mountain_p),
                icon  = Icons.Rounded.Hiking
            ),
            BEACHES(
                title = UiText.StringResource(R.string.beach_p),
                icon  = Icons.Rounded.BeachAccess
            ),
            NATIONAL_PARKS(
                title = UiText.StringResource(R.string.national_parks_p),
                icon  = Icons.Rounded.NaturePeople
            ),
            JUNGLES(
                title = UiText.StringResource(R.string.jungles_p),
                icon  = Icons.Rounded.Nature
            ),
            FORESTS(
                title = UiText.StringResource(R.string.forests_p),
                icon  = Icons.Rounded.Forest
            ),
            SAFARIS(
                title = UiText.StringResource(R.string.safaris_p),
                icon  = Icons.Rounded.PhotoCamera
            ),
            DESERTS(
                title = UiText.StringResource(R.string.deserts_p),
                icon  = Icons.Rounded.WbSunny
            ),
        }

        fun getValues() : List<PreferenceModel>{
            return  Types.entries.map { item ->  PreferenceModel(
                id    = item.name,
                title = item.title,
                icon  = item.icon
            ) }.toList()
        }
    }
}

class CultureHistory   {
    companion object {
        enum class Types(val title: UiText,val icon: ImageVector) {
            HISTORIC_CITIES(
                title = UiText.StringResource(R.string.historic_cities_p),
                icon  = Icons.Rounded.LocationCity
            ),
            MUSEUMS(
                title = UiText.StringResource(R.string.museums_p),
                icon  = Icons.Rounded.Museum
            ),
            ART_GALLERIES(
                title = UiText.StringResource(R.string.art_galleries_p),
                icon  = Icons.Rounded.FormatPaint
            ),
            ARCHAEOLOGICAL_SITES(
                title = UiText.StringResource(R.string.archaeological_sites_p),
                icon  = Icons.Rounded.Place
            ),
            FESTIVALS(
                title = UiText.StringResource(R.string.festivals_p),
                icon  = Icons.Rounded.Festival
            ),
            CULTURAL_EVENTS(
                title = UiText.StringResource(R.string.cultural_events_p),
                icon  = Icons.Rounded.Event
            ),
            HISTORICAL_MONUMENTS(
                title = UiText.StringResource(R.string.historical_monuments_p),
                icon  = Icons.Rounded.TempleBuddhist
            ),
            CASTLES(
                title = UiText.StringResource(R.string.castles_p),
                icon  = Icons.Rounded.Castle
            ),
        }

        fun getValues() : List<PreferenceModel>{
            return  Types.entries.map { item ->  PreferenceModel(
                id    = item.name,
                title = item.title,
                icon  = item.icon
            ) }.toList()
        }
    }
}

class Relaxation    {
    companion object {
        enum class Types(val title: UiText,val icon: ImageVector) {
            SPAS(
                title = UiText.StringResource(R.string.spas_p),
                icon  = Icons.Rounded.Spa
            ),
            TROPICAL_ISLANDS(
                title = UiText.StringResource(R.string.tropical_islands_p),
                icon  = Icons.Rounded.Landscape
            ),
            RESORTS(
                title = UiText.StringResource(R.string.resorts_p),
                icon  = Icons.Rounded.HotelClass
            ),
            YOGA(
                title = UiText.StringResource(R.string.yoga_p),
                icon  = Icons.Rounded.BubbleChart
            ),
            MEDITATION(
                title = UiText.StringResource(R.string.meditation_p),
                icon  = Icons.Rounded.Air
            ),
            SPA_CENTERS(
                title = UiText.StringResource(R.string.spa_centers_p),
                icon  = Icons.Rounded.Spa
            ),
            HOTELS(
                title = UiText.StringResource(R.string.hotels_p),
                icon  = Icons.Rounded.Hotel
            )
        }

        fun getValues() : List<PreferenceModel>{
            return  Types.entries.map { item ->  PreferenceModel(
                id    = item.name,
                title = item.title,
                icon  = item.icon
            ) }.toList()
        }
    }
}