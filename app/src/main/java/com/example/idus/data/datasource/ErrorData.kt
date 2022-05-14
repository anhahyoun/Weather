package com.example.idus.data.datasource

sealed class ErrorData(
    override val message: String? = null,
    override val cause: Throwable? = null
) : Exception(message, cause) {
    data class HttpError(override val message: String, val code: Int) : ErrorData()
}