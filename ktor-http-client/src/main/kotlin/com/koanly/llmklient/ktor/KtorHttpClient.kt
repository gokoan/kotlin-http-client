package com.koanly.llmklient.ktor

import com.koanly.httpclient.IHttpClient
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.apache.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.http.*


class KtorHttpClient(
    private val httpClient: HttpClient = HttpClient(Apache) {
        install(HttpTimeout)
        followRedirects = false
        engine {
            // Timeouts máximos del engine
            socketTimeout = 5 * 60 * 1000
            connectTimeout = 5 * 60 * 1000
            connectionRequestTimeout = 5 * 60 * 1000
        }
    }
): IHttpClient {

    override suspend fun post(
        url: String,
        body: String,
        timeout: Long,
        authBearer: String?,
        contentType: String
    ): String {
        try {
            val httpResponse = httpClient.post(url) {
                timeout {
                    requestTimeoutMillis = timeout
                }
                header(HttpHeaders.ContentType, "application/json")
                header("Authorization", "Bearer $authBearer")
                setBody(body)
            }
            return httpResponse.body()
        } catch (ce: ClientRequestException) {
            throw IHttpClient.HttpClientException(
                httpStatus = IHttpClient.HttpStatus(ce.response.status.value),
                msg = ce.message,
                t = ce
            )
        }
    }
}