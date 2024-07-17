package com.spherixlabs.trekscape.recommendations.data

import com.spherixlabs.trekscape.core.domain.utils.results.DataError
import com.spherixlabs.trekscape.core.domain.utils.results.Result
import com.spherixlabs.trekscape.core.utils.coordinates.model.CoordinatesData
import com.spherixlabs.trekscape.home.domain.enums.LocationPreference
import com.spherixlabs.trekscape.recommendations.domain.model.PlaceRecommendation
import com.spherixlabs.trekscape.recommendations.domain.repository.PlaceRecommendationsRepository
import kotlinx.coroutines.delay
import javax.inject.Inject

/**
 * [PlaceRecommendationsRepositoryImpl] is a implementation of [PlaceRecommendationsRepository].
 * */
class PlaceRecommendationsRepositoryImpl @Inject constructor(

): PlaceRecommendationsRepository {
    override suspend fun getSomeRecommendations(
        quantity: Int,
        ownPreferences: List<String>,
        locationPreference: LocationPreference,
        currentLocation: CoordinatesData?
    ): Result<List<PlaceRecommendation>, DataError.Network> {
        delay(5000)
        return Result.Success(burnedTestData())
    }

    private fun burnedTestData(): List<PlaceRecommendation> = listOf(
        PlaceRecommendation(
            name = "Machu Picchu, Perú",
            description = "Ubicada en la cima de una montaña en los Andes peruanos, Machu Picchu es una antigua ciudadela inca que es considerada una de las Siete Maravillas del Mundo Moderno. Los visitantes pueden explorar las ruinas de la ciudad, que incluyen templos, casas y plazas, o realizar una caminata por el Camino Inca, un sendero de 43 kilómetros que conduce a Machu Picchu.\n" +
                    "Atracciones: Santuario Histórico de Machu Picchu, Camino Inca, Huayna Picchu, Montaña Machu Picchu.\n" +
                    "Actividades: Senderismo, exploración de ruinas, observación de aves, fotografía.",
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/e/eb/Machu_Picchu%2C_Peru.jpg",
            location = CoordinatesData(
                latitude = -13.154444,
                longitude = -72.524167,
            )
        ),
        PlaceRecommendation(
            name = "Petra, Jordania",
            description = "Petra es una antigua ciudad tallada en la ladera de una montaña de arenisca en Jordania. Es conocida como la \"Ciudad Rosa\" por el color de las rocas. Los visitantes pueden explorar las ruinas de la ciudad, que incluyen tumbas, templos y un teatro, o realizar un paseo a caballo por el cañón.\n" +
                    "Atracciones: El Siq, El Tesoro, El Monasterio, Tumbas Reales.\n" +
                    "Actividades: Senderismo, paseos a caballo, exploración de ruinas, visitas guiadas.",
            imageUrl = "https://media.traveler.es/photos/613768a4ba2a75fcba4be720/master/w_1600%2Cc_limit/154153.jpg",
            location = CoordinatesData(
                latitude = 30.533333,
                longitude = 35.666667,
            )
        ),
        PlaceRecommendation(
            name = "Gran Barrera de Coral, Australia",
            description = "La Gran Barrera de Coral es el sistema de arrecifes de coral más grande del mundo. Se encuentra frente a la costa de Queensland, Australia, y alberga una gran variedad de vida marina. Los visitantes pueden bucear o hacer snorkel en el arrecife, tomar un tour en bote con fondo de vidrio o visitar una de las muchas islas del arrecife.\n" +
                    "Atracciones: Arrecife de coral, vida marina, islas, tours en barco.\n" +
                    "Actividades: Buceo, snorkel, natación, paseos en bote.",
            imageUrl = "https://static.nationalgeographic.es/files/styles/image_3200/public/01greatbarrierreef.jpg?w=1190&h=792",
            location = CoordinatesData(
                latitude = -17.000000,
                longitude = 145.000000,
            )
        ),
        PlaceRecommendation(
            name = "Auroras Boreales, Noruega",
            description = "La aurora boreal, también conocida como la aurora boreal, es un fenómeno natural de luz en el cielo. Es causado por la colisión de partículas cargadas del sol con la atmósfera de la Tierra. Las auroras boreales son más visibles en las regiones árticas y subárticas, y se pueden ver en Noruega desde finales de otoño hasta principios de primavera.\n" +
                    "Atracciones: Auroras boreales, paisajes árticos, trineos de perros, esquí.\n" +
                    "Actividades: Observación de la aurora boreal, trineos de perros, esquí, motos de nieve.",
            imageUrl = "https://viajes.nationalgeographic.com.es/medio/2023/09/20/lofoten-boreal_04cb6545_230920195855_1280x880.jpg",
            location = CoordinatesData(
                latitude = 66.000000,
                longitude = 18.000000,
            )
        ),
        PlaceRecommendation(
            name = "Taj Mahal, India",
            description = "El Taj Mahal es un mausoleo de mármol blanco marfil en la ciudad india de Agra. Fue construido por el emperador mogol Shah Jahan en memoria de su tercera esposa, Mumtaz Mahal. El Taj Mahal es considerado una de las obras maestras de la arquitectura islámica y es uno de los destinos turísticos más populares de la India.\n" +
                    "Atracciones: Taj Mahal, Fuerte de Agra, Tumbas de Mughal, Jardines de Mehtab Bagh.\n" +
                    "Actividades: Visitas guiadas, fotografía, compras, degustación de comida.",
            imageUrl = "https://www.ngenespanol.com/wp-content/uploads/2023/08/Taj-Mahal.jpeg",
            location = CoordinatesData(
                latitude = 27.283333,
                longitude = 78.000000,
            )
        ),
    )
}