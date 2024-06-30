package com.spherixlabs.trekscape.core.domain.utils.results

/**
 * The [Result] sealed class is used to represent the result of an operation.
 *
 * @param D - for data type.
 * @param E [com.spherixlabs.trekscape.core.domain.utils.results.Error] - for error type.
 * */
sealed interface Result<out D, out E: Error> {
    /**
     * The [Success] class represents the successful result of an operation.
     *
     * @param D - for data type.
     * @return [Result]<[D], [Nothing]>
     * */
    data class Success<out D>(val data: D) : Result<D, Nothing>
    /**
     * The [Error] class represents the error result of an operation.
     *
     * @param E [com.spherixlabs.trekscape.core.domain.utils.results.Error] - for error type.
     * @return [Result]<[Nothing], [E]>
     * */
    data class Error<out E: com.spherixlabs.trekscape.core.domain.utils.results.Error>(val error: E) :
        Result<Nothing, E>
}

inline fun <T, E: Error, R> Result<T, E>.map(
    map: (T) -> R
): Result<R, E> {
    return when (this) {
        is Result.Success -> Result.Success(map(this.data))
        is Result.Error -> Result.Error(this.error)
    }
}

fun <T, E: Error> Result<T, E>.asEmptyDataResult(): EmptyResult<E> {
    return map {  }
}

typealias EmptyResult<E> = Result<Unit, E>