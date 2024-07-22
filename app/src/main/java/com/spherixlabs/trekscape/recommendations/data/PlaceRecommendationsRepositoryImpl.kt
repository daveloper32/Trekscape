package com.spherixlabs.trekscape.recommendations.data

import android.net.Uri
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.GenerateContentResponse
import com.spherixlabs.trekscape.BuildConfig
import com.spherixlabs.trekscape.core.domain.utils.results.DataError
import com.spherixlabs.trekscape.core.domain.utils.results.Result
import com.spherixlabs.trekscape.core.utils.constants.Constants
import com.spherixlabs.trekscape.core.utils.constants.Constants.EMPTY_STR
import com.spherixlabs.trekscape.core.utils.constants.Constants.GEMINI_MODEL_NAME
import com.spherixlabs.trekscape.core.utils.coordinates.model.CoordinatesData
import com.spherixlabs.trekscape.home.domain.enums.LocationPreference
import com.spherixlabs.trekscape.recommendations.data.utils.PlacesUtils
import com.spherixlabs.trekscape.recommendations.domain.model.PlaceRecommendation
import com.spherixlabs.trekscape.recommendations.domain.repository.PlaceRecommendationsRepository
import org.json.JSONArray
import timber.log.Timber
import javax.inject.Inject

/**
 * [PlaceRecommendationsRepositoryImpl] is a implementation of [PlaceRecommendationsRepository].
 * */
class PlaceRecommendationsRepositoryImpl @Inject constructor(
    private val placesUtils : PlacesUtils,
): PlaceRecommendationsRepository {
    override suspend fun getSomeRecommendations(
        quantity           : Int,
        ownPreferences     : List<String>,
        locationPreference : LocationPreference,
        currentLocation    : CoordinatesData?,
        languageCode       : String
    ): Result<List<PlaceRecommendation>, DataError.Network> {
        val generativeModel = GenerativeModel(
            modelName = GEMINI_MODEL_NAME,
            apiKey    = BuildConfig.GEMINI_API_KEY,
        )
        val prompt = generatePrompt(
            quantity           = quantity,
            ownPreferences     = ownPreferences,
            locationPreference = locationPreference,
            currentLocation    = currentLocation,
            languageCode       = languageCode
        )
        Timber.d("Prompt: $prompt")
        val response = generativeModel.generateContent(prompt)
        return Result.Success(cleanAndMapResponse(response))
    }

    /**
     * This function generates a prompt for the generative model based on the specified parameters.
     * @param quantity [Int] The number of recommendations to retrieve.
     * @param ownPreferences [List]<[String]> The list of preferences that the user has set.
     * @param locationPreference [LocationPreference] The location preference for the recommendations.
     * @param currentLocation [CoordinatesData]? The current location of the user. It will only be
     * used if if the location preference is not set to [LocationPreference.ALL_WORLD].
     * @param languageCode [String] The language code for the recommendations.
     *
     * @return [String] The generated prompt for the generative model.
     * */
    private fun generatePrompt(
        quantity           : Int,
        ownPreferences     : List<String>,
        locationPreference : LocationPreference,
        currentLocation    : CoordinatesData?,
        languageCode       : String
    ): String {
        val locationPreferences: String = getLocationPreferencePhrasePrompt(
            locationPreference = locationPreference,
            currentLocation    = currentLocation,
        )
        return """
            You are the best travel planner in the world. You know everything about travel and how
            to recommend places to travel to people based on their own preferences and current location.
            Generate ${quantity} recommendations for travel places using the following parameters:
            - JSON schema for a place:
                {
                    "name": "Place name",
                    "description": "Place description (including attractions and activities)",
                    "coordinates": {
                        "latitude": Latitude in double format,
                        "longitude": Longitude in double format
                    }
                }
            - Search based on the following preferences: ${ownPreferences.joinToString(", ")}
            - Location preferences: ${locationPreferences}
            - Language to generate data: $languageCode
            - Format the response as a JSONArray of objects of the provided JSON schema
        """.trimIndent()
    }

    /**
     * This function generates a phrase prompt based on the specified location parameters.
     *
     * @param locationPreference [LocationPreference] The location preference for the recommendations.
     * @param currentLocation [CoordinatesData]? The current location of the user. It will only be
     * used if if the location preference is not set to [LocationPreference.ALL_WORLD].
     *
     * @return [String] The generated phrase prompt.
     * */
    private fun getLocationPreferencePhrasePrompt(
        locationPreference : LocationPreference,
        currentLocation    : CoordinatesData?,
    ): String {
        val locationPreferenceExplanation: String = when (
            locationPreference
        ) {
            LocationPreference.ALL_WORLD -> "all over the world"
            LocationPreference.SAME_CONTINENT -> "the same continent"
            LocationPreference.SAME_COUNTRY -> "the same country"
            LocationPreference.SAME_CITY -> "the same city"
        }
        val extraDetailsLocationPreferenceExplanation: String = when (
            locationPreference
        ) {
            LocationPreference.ALL_WORLD -> ". You should recommend places in different countries, also do not recommend more than one place in the same country"
            LocationPreference.SAME_CONTINENT -> ". You should recommend places in different countries, you can't recommend places in the same country where the provided coordinates are, also do not recommend more than one place in the same country"
            LocationPreference.SAME_COUNTRY -> ". You should recommend places in different cities, you can't recommend places in the same city where the provided coordinates are, also do not recommend more than one place in the same city"
            LocationPreference.SAME_CITY -> ""
        }
        return if (
            locationPreference != LocationPreference.ALL_WORLD &&
            currentLocation != null
        ) {
            "Filter the results searching on ${locationPreferenceExplanation} where this coordinates (${currentLocation.latitude}, ${currentLocation.longitude}) are$extraDetailsLocationPreferenceExplanation"
        } else {
            "Filter the results searching on all over the world"
        }
    }

    /**
     * This function cleans and maps the response from the generative model to a list of [PlaceRecommendation].
     *
     * @param response [GenerateContentResponse] The response from the generative model.
     * @return [List]<[PlaceRecommendation]> The cleaned and mapped response.
     * */
    private suspend fun cleanAndMapResponse(
        response : GenerateContentResponse
    ): List<PlaceRecommendation> {
        return try {
            val mappedData: MutableList<PlaceRecommendation> = mutableListOf()
            val responseCleaned: String = response.text
                ?.replace("`", "")
                ?.replace("json", "")
                ?: EMPTY_STR
            if (responseCleaned.isNotEmpty()) {
                val responseAsJSONArray: JSONArray = JSONArray(responseCleaned)
                for (i in 0 until responseAsJSONArray.length()) {
                    val placeObject = responseAsJSONArray.getJSONObject(i)
                    val name = placeObject.getString(Constants.JsonPlaceRecommendation.NAME)
                    val description = placeObject.getString(Constants.JsonPlaceRecommendation.DESCRIPTION)
                    val coordinatesObject = placeObject.getJSONObject(Constants.JsonPlaceRecommendation.COORDINATES)
                    val latitude = coordinatesObject.getDouble(Constants.JsonPlaceRecommendation.LATITUDE)
                    val longitude = coordinatesObject.getDouble(Constants.JsonPlaceRecommendation.LONGITUDE)
                    mappedData.add(
                        PlaceRecommendation(
                            name = name,
                            description = description,
                            location = CoordinatesData(
                                latitude = latitude,
                                longitude = longitude
                            ),
                            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/e/eb/Machu_Picchu%2C_Peru.jpg",
                        )
                    )
                }
            }
            tryToFetchImagesToRecommendations(mappedData)
        } catch (e: Exception) {
           emptyList()
        }
    }

    /**
     * This function tries to fetch images to recommendations.
     *
     * @param data [List]<[PlaceRecommendation]> The list of recommendations.
     * @return [List]<[PlaceRecommendation]> The list of recommendations with images.
     * */
    private suspend fun tryToFetchImagesToRecommendations(
        data : List<PlaceRecommendation>
    ) : List<PlaceRecommendation> {
        return try {
            val dataWithImages: MutableList<PlaceRecommendation> = mutableListOf()
            data.forEach { placeRecommendation ->
                dataWithImages.add(
                    tryToFetchImageToRecommendation(placeRecommendation)
                )
            }
            dataWithImages
        } catch (e: Exception) { data }
    }

    /**
     * This function tries to fetch an image to a recommendation.
     *
     * @param data [PlaceRecommendation] The recommendation.
     * @return [PlaceRecommendation] The recommendation with an image.
     * */
    private suspend fun tryToFetchImageToRecommendation(
        data : PlaceRecommendation
    ): PlaceRecommendation {
        return try {
            val placeIdOfFirstPrediction: String = placesUtils
                .getAutocompletePredictions(
                    apiKey   = BuildConfig.PLACES_API_KEY,
                    query    = data.name,
                    location = data.location,
                )?.firstOrNull()?.placeId ?: return data
            val photo: Uri = placesUtils
                .getFirstAvailablePhotoFromPlace(
                    apiKey  = BuildConfig.PLACES_API_KEY,
                    placeId = placeIdOfFirstPrediction,
                ) ?: return data
            data.copy(
                imageUrl = photo.toString(),
            )
        } catch (e: Exception) { data }
    }
}