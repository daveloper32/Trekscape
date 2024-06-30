package com.spherixlabs.trekscape.core.utils.http

import com.spherixlabs.trekscape.core.domain.utils.results.DataError
import com.spherixlabs.trekscape.core.domain.utils.results.Result
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.CancellationException
import kotlinx.serialization.SerializationException

suspend inline fun <reified Response: Any> HttpClient.get(
    route : String,
    query : Map<String, Any> = mapOf(),
): Result<Response, DataError.Network> {
    return safeCall {
        get {
            url(route)
            query.forEach { (key, value) ->
                parameter(key, value)
            }
        }
    }
}

suspend inline fun <reified Request, reified Response: Any> HttpClient.post(
    route : String,
    body  : Request
): Result<Response, DataError.Network> {
    return safeCall {
        post {
            url(route)
            setBody(body)
        }
    }
}

suspend inline fun <reified Response: Any> HttpClient.delete(
    route : String,
    query : Map<String, Any> = mapOf(),
): Result<Response, DataError.Network> {
    return safeCall {
        delete {
            url(route)
            query.forEach { (key, value) ->
                parameter(key, value)
            }
        }
    }
}

suspend inline fun <reified T> safeCall(
    execute : () -> HttpResponse
): Result<T, DataError.Network>  {
    val response = try {
        execute()
    } catch (e: UnresolvedAddressException) {
        e.printStackTrace()
        return Result.Error(DataError.Network.NOT_INTERNET)
    } catch (e: SerializationException) {
        e.printStackTrace()
        return Result.Error(DataError.Network.SERIALIZATION)
    }  catch (e: SocketTimeoutException) {
        e.printStackTrace()
        return Result.Error(DataError.Network.REQUEST_TIMEOUT)
    } catch (e: Exception) {
        if (e is CancellationException) {
            throw e
        }
        e.printStackTrace()
        return Result.Error(DataError.Network.UNKNOWN)
    }
    return responseToResult(response)
}

suspend inline fun <reified T> responseToResult(
    response : HttpResponse
): Result<T, DataError.Network> {
    return when (response.status.value) {
        in 200..299 -> Result.Success(response.body<T>())
        400               -> Result.Error(DataError.Network.NOT_FOUND)
        401               -> Result.Error(DataError.Network.UNAUTHORIZED)
        408               -> Result.Error(DataError.Network.REQUEST_TIMEOUT)
        409               -> Result.Error(DataError.Network.CONFLICT)
        413               -> Result.Error(DataError.Network.PAYLOAD_TOO_LARGE)
        429               -> Result.Error(DataError.Network.TOO_MANY_REQUESTS)
        in 500..599 -> Result.Error(DataError.Network.SERVER_ERROR)
        else              -> Result.Error(DataError.Network.UNKNOWN)
    }
}