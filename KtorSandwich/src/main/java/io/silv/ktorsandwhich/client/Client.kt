package io.silv.ktorsandwhich.client

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.silv.ktorsandwhich.ApiResponse
import io.silv.ktorsandwhich.KSandwichInitializer


interface KSandwichClient {

    val client: HttpClient

    companion object {

        fun create(): KSandwichClient {
            return KSandwichClientImpl()
        }
    }
}

internal class KSandwichClientImpl: KSandwichClient {

    override val client = HttpClient(
        KSandwichInitializer.engineFactory {}
    ) {

    }

}

suspend inline fun <reified T> KSandwichClient.get(
    urlString: String,
    crossinline block: HttpRequestBuilder.() -> Unit = {}
): ApiResponse<T> {
    return client.getApiResponse(urlString, block)
}

suspend inline fun <reified T> KSandwichClient.post(
    urlString: String,
    block: HttpRequestBuilder.() -> Unit = {}
): ApiResponse<T> {
    val response = client.post(urlString, block)
    return ApiResponse.of { response }
}

suspend inline fun <reified T> KSandwichClient.put(
    urlString: String,
    block: HttpRequestBuilder.() -> Unit = {}
): ApiResponse<T> {
    val response = client.put(urlString, block)
    return ApiResponse.of { response }
}

suspend inline fun <reified T> KSandwichClient.patch(
    urlString: String,
    block: HttpRequestBuilder.() -> Unit = {}
): ApiResponse<T> {
    val response = client.patch(urlString, block)
    return ApiResponse.of { response }
}


suspend inline fun <reified T> HttpClient.getApiResponse(
    urlString: String, crossinline block: HttpRequestBuilder.() -> Unit
): ApiResponse<T> {
    return ApiResponse.of { get(urlString, block) }
}

