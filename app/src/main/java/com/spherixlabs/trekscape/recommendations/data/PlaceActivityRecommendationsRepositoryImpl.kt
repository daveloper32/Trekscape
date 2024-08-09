package com.spherixlabs.trekscape.recommendations.data

import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.GenerateContentResponse
import com.spherixlabs.trekscape.BuildConfig
import com.spherixlabs.trekscape.core.domain.utils.results.DataError
import com.spherixlabs.trekscape.core.domain.utils.results.Result
import com.spherixlabs.trekscape.core.utils.constants.Constants
import com.spherixlabs.trekscape.core.utils.constants.Constants.EMPTY_STR
import com.spherixlabs.trekscape.core.utils.constants.Constants.GEMINI_MODEL_NAME
import com.spherixlabs.trekscape.recommendations.domain.model.PlaceActivityRecommendation
import com.spherixlabs.trekscape.recommendations.domain.model.PlaceRecommendation
import com.spherixlabs.trekscape.recommendations.domain.repository.PlaceActivityRecommendationsRepository
import com.spherixlabs.trekscape.recommendations.domain.repository.PlaceRecommendationsRepository
import org.json.JSONArray
import timber.log.Timber
import javax.inject.Inject

/**
 * [PlaceActivityRecommendationsRepositoryImpl] is a implementation of [PlaceRecommendationsRepository].
 * */
class PlaceActivityRecommendationsRepositoryImpl @Inject constructor(
): PlaceActivityRecommendationsRepository {
    override suspend fun getSomeRecommendations(
        placeName: String,
        customApiKey: String?,
        ownPreferences: List<String>,
        languageCode: String
    ): Result<List<PlaceActivityRecommendation>, DataError.Network> {
        val generativeModel = GenerativeModel(
            modelName = GEMINI_MODEL_NAME,
            apiKey    = if (customApiKey != null) customApiKey else BuildConfig.GEMINI_API_KEY,
        )
        val prompt = generatePrompt(
            placeName          = placeName,
            ownPreferences     = ownPreferences,
            languageCode       = languageCode,
        )
        Timber.d("Prompt: $prompt")
        return try {
            val response: GenerateContentResponse = generativeModel.generateContent(prompt)
            val mappedResponse: List<PlaceActivityRecommendation> = cleanAndMapResponse(response)
            Result.Success(mappedResponse)
        } catch (e: Exception) {
            Result.Error(DataError.Network.NOT_FOUND)
        }
    }

    /**
     * This function generates a prompt for the generative model based on the specified parameters.
     * @param placeName [String] The name of the place.
     * @param ownPreferences [List]<[String]> The list of preferences that the user has set.
     * @param languageCode [String] The language code for the recommendations.
     *
     * @return [String] The generated prompt for the generative model.
     * */
    private fun generatePrompt(
        placeName      : String,
        ownPreferences : List<String>,
        languageCode   : String,
    ): String {
        return """
            You are the best travel planner in the world. You know everything about travel and how
            to recommend activities to do in a place to people based on their own preferences.
            Generate a itinerary of activities for the following place: $placeName (minimum 1,
             maximum 10 activities) using the following parameters:
            - JSON schema for a place:
                {
                    "name": "Activity/place name",
                    "description": "Activity/place description, possible costs",
                }
            - Make the activity itinerary based on the following preferences: ${ownPreferences.joinToString(", ")}
            - Language to generate data: $languageCode
            - Format the response as a JSONArray of objects of the provided JSON schema
        """.trimIndent()
    }

    /**
     * This function cleans and maps the response from the generative model to a list of [PlaceActivityRecommendation].
     *
     * @param response [GenerateContentResponse] The response from the generative model.
     * @return [List]<[PlaceRecommendation]> The cleaned and mapped response.
     * */
    private suspend fun cleanAndMapResponse(
        response : GenerateContentResponse
    ): List<PlaceActivityRecommendation> {
        return try {
            val mappedData: MutableList<PlaceActivityRecommendation> = mutableListOf()
            val responseCleaned: String = response.text
                ?.replace("`", "")
                ?.replace("json", "")
                ?: EMPTY_STR
            if (responseCleaned.isNotEmpty()) {
                val responseAsJSONArray: JSONArray = JSONArray(responseCleaned)
                for (i in 0 until responseAsJSONArray.length()) {
                    val activityObject = responseAsJSONArray.getJSONObject(i)
                    val name = activityObject.getString(Constants.JsonPlaceActivityRecommendation.NAME)
                    val description = activityObject.getString(Constants.JsonPlaceActivityRecommendation.DESCRIPTION)
                    mappedData.add(
                        PlaceActivityRecommendation(
                            name = name,
                            description = description,
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