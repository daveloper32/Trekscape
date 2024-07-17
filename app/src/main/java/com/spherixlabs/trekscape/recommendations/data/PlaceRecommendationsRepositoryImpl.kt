package com.spherixlabs.trekscape.recommendations.data

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
import com.spherixlabs.trekscape.recommendations.domain.model.PlaceRecommendation
import com.spherixlabs.trekscape.recommendations.domain.repository.PlaceRecommendationsRepository
import org.json.JSONArray
import javax.inject.Inject

/**
 * [PlaceRecommendationsRepositoryImpl] is a implementation of [PlaceRecommendationsRepository].
 * */
class PlaceRecommendationsRepositoryImpl @Inject constructor(

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
     * */
    private fun generatePrompt(
        quantity           : Int,
        ownPreferences     : List<String>,
        locationPreference : LocationPreference,
        currentLocation    : CoordinatesData?,
        languageCode       : String
    ): String {
        val locationPreferences: String = if (
            locationPreference != LocationPreference.ALL_WORLD &&
            currentLocation != null
        ) {
            "Search on the ${locationPreference.name} of the coordinates (${currentLocation.latitude}, ${currentLocation.longitude})"
        } else {
            "Search on ${LocationPreference.ALL_WORLD.name}"
        }
        return """
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
            - Location preference: ${locationPreferences}
            - Language to generate data: $languageCode
            - Format the response as a JSONArray of objects of the provided JSON schema
        """.trimIndent()
    }

    /**
     * This function cleans and maps the response from the generative model to a list of [PlaceRecommendation].
     *
     * @param response [GenerateContentResponse] The response from the generative model.
     * @return [List]<[PlaceRecommendation]> The cleaned and mapped response.
     * */
    private fun cleanAndMapResponse(
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
            mappedData
        } catch (e: Exception) {
           emptyList()
        }
    }
}