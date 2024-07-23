package com.spherixlabs.trekscape.recommendations.data.utils

import android.content.Context
import android.net.Uri
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.AutocompletePrediction
import com.google.android.libraries.places.api.model.AutocompleteSessionToken
import com.google.android.libraries.places.api.model.PhotoMetadata
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.model.RectangularBounds
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.FetchPlaceResponse
import com.google.android.libraries.places.api.net.FetchResolvedPhotoUriRequest
import com.google.android.libraries.places.api.net.FetchResolvedPhotoUriResponse
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsResponse
import com.google.android.libraries.places.api.net.PlacesClient
import com.spherixlabs.trekscape.core.utils.coordinates.CoordinatesUtils
import com.spherixlabs.trekscape.core.domain.model.CoordinatesData
import com.spherixlabs.trekscape.core.utils.maps.MapsUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * [PlacesUtils] is a utility class that provides methods for easily request and fetch data from Google Places API.
 * */
class PlacesUtils @Inject constructor(
    val context : Context,
) {
    /**
     * Initializes the Places API client.
     *
     * @param apiKey [String] The API key for the Places API.
     * @return [PlacesClient] The initialized Places client.
     * */
    fun initAndGetPlacesClient(
        apiKey  : String,
    ): PlacesClient {
        if (!Places.isInitialized()) {
            Places.initializeWithNewPlacesApiEnabled(context, apiKey)
        }
        return Places.createClient(context)
    }
    /**
     * This function fetches autocomplete predictions of some provided query from the Google Places API.
     *
     * @param apiKey [String] The API key for the Google Places API.
     * @param query [String] The query to search for.
     * @param placesClient [PlacesClient] The Places client to use.
     * @param token [AutocompleteSessionToken] The session token to use.
     * @param location [CoordinatesData] The location to search for.
     * @param boundsPadding [Double] The padding for the bounds.
     * @return [List]<[AutocompletePrediction]>? The list of autocomplete predictions.
     * */
    suspend fun getAutocompletePredictions(
        apiKey        : String,
        query         : String,
        placesClient  : PlacesClient = initAndGetPlacesClient(apiKey),
        token         : AutocompleteSessionToken = AutocompleteSessionToken.newInstance(),
        location      : CoordinatesData? = null,
        boundsPadding : Double = 0.1,
    ): List<AutocompletePrediction>? {
        return try {
            if (
                apiKey.isEmpty() &&
                query.isEmpty()
            ) {
                return null
            }
            withContext(Dispatchers.IO) {
                val bounds: RectangularBounds? = if (location != null) {
                    val squarePolygonOnCoordinates: CoordinatesUtils.SquaredPolygon? =
                        CoordinatesUtils.generateSquaredPolygonOnSomeCoordinates(
                            coordinates = listOf(location),
                            padding     = boundsPadding,
                        )
                    if (squarePolygonOnCoordinates != null) {
                        RectangularBounds.newInstance(
                            MapsUtils.fromCoordinatesDataToLatLng(squarePolygonOnCoordinates.southWestCoordinate),
                            MapsUtils.fromCoordinatesDataToLatLng(squarePolygonOnCoordinates.northEastCoordinate),
                        )
                    } else {
                        null
                    }
                } else {
                    null
                }
                val request: FindAutocompletePredictionsRequest = FindAutocompletePredictionsRequest
                    .builder()
                    .setLocationBias(bounds)
                    .setOrigin(MapsUtils.fromNullCoordinatesDataToLatLng(location))
                    .setSessionToken(token)
                    .setQuery(query)
                    .build()
                val response: Task<FindAutocompletePredictionsResponse> =
                    placesClient.findAutocompletePredictions(request)
                Tasks.await(
                    response,
                    120L,
                    TimeUnit.SECONDS
                )
                var results: List<AutocompletePrediction>? = null
                if (response.isSuccessful) {
                    response.result?.let {
                        results = it.autocompletePredictions
                    }
                }
                results
            }
        } catch (e: Exception) { null }
    }

    /**
     * This function fetches a place from its ID from the Google Places API.
     *
     * @param apiKey [String] The API key for the Google Places API.
     * @param placeId [String] The ID of the place to fetch.
     * @param placesClient [PlacesClient] The Places client to use.
     * @param fieldsToFetch [List]<[Place.Field]> The fields to fetch.
     * @return [Place]? The fetched place.
     * */
    suspend fun getPlaceFromId(
        apiKey        : String,
        placeId       : String,
        placesClient  : PlacesClient = initAndGetPlacesClient(apiKey),
        fieldsToFetch : List<Place.Field> = listOf(
            Place.Field.NAME,
            Place.Field.ADDRESS,
            Place.Field.LAT_LNG,
        )
    ): Place? {
        return try {
            if (
                apiKey.isEmpty() &&
                placeId.isEmpty()
            ) {
                return null
            }
            withContext(Dispatchers.IO) {
                val request: FetchPlaceRequest = FetchPlaceRequest
                    .builder(
                        placeId,
                        fieldsToFetch
                    ).build()
                val response: Task<FetchPlaceResponse> = placesClient
                    .fetchPlace(request)
                Tasks.await(
                    response,
                    120L,
                    TimeUnit.SECONDS
                )
                var placeFound: Place? = null
                if (response.isSuccessful) {
                    response.result?.let {
                        placeFound = it.place
                    }
                }
                placeFound
            }
        } catch (e: Exception) { null }
    }

    /**
     * This function fetches the first available photo from a place from the Google Places API.
     *
     * @param apiKey [String] The API key for the Google Places API.
     * @param placeId [String] The ID of the place to fetch.
     * @param placesClient [PlacesClient] The Places client to use.
     * @param maxPhotoWidth [Int] The maximum width of the photo.
     * @param maxPhotoHeight [Int] The maximum height of the photo.
     * @return [Uri]? The URI of the first available photo.
     * */
    suspend fun getFirstAvailablePhotoFromPlace(
        apiKey         : String,
        placeId        : String,
        placesClient   : PlacesClient = initAndGetPlacesClient(apiKey),
        maxPhotoWidth  : Int = 500,
        maxPhotoHeight : Int = 300,
    ): Uri? {
        return try {
            if (
                apiKey.isEmpty() &&
                placeId.isEmpty()
            ) {
                return null
            }
            val place: Place = getPlaceFromId(
                apiKey        = apiKey,
                placeId       = placeId,
                placesClient  = placesClient,
                fieldsToFetch = listOf(
                    Place.Field.PHOTO_METADATAS,
                ),
            ) ?: return null
            if (
                place.photoMetadatas.isNullOrEmpty()
            ) {
                return null
            }
            val firstPhotoMetadata: PhotoMetadata = place.photoMetadatas?.firstOrNull()?: return null
            withContext(Dispatchers.IO) {
                val request: FetchResolvedPhotoUriRequest = FetchResolvedPhotoUriRequest
                    .builder(
                        firstPhotoMetadata
                    ).setMaxWidth(maxPhotoWidth)
                    .setMaxHeight(maxPhotoHeight)
                    .build()
                val response: Task<FetchResolvedPhotoUriResponse> =
                    placesClient.fetchResolvedPhotoUri(request)
                Tasks.await(
                    response,
                    120L,
                    TimeUnit.SECONDS
                )
                var placePhotoFound: Uri? = null
                if (response.isSuccessful) {
                    response.result?.let {
                        placePhotoFound = it.uri
                    }
                }
                placePhotoFound
            }
        } catch (e: Exception) { null }
    }
}