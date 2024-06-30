package com.spherixlabs.trekscape.core.domain.utils.results

sealed interface DataError: Error {
    enum class Network: DataError {
        REQUEST_TIMEOUT,
        NOT_FOUND,
        UNAUTHORIZED,
        CONFLICT,
        TOO_MANY_REQUESTS,
        NOT_INTERNET,
        PAYLOAD_TOO_LARGE,
        SERVER_ERROR,
        SERIALIZATION,
        UNKNOWN,
    }
}