package com.koanly.httpclient

interface IHttpClient {

    suspend fun post(
        url: String,
        body: String,
        timeout: Long,
        authBearer: String? = null,
        contentType: String = "application/json"
    ): String

    class HttpClientException(val httpStatus: HttpStatus, msg: String, val t: Throwable): Exception(msg, t)

    data class HttpStatus(val code: Int) {
        companion object {
            val OK = 200
            val BAD_REQUEST = 400
        }
    }
}
