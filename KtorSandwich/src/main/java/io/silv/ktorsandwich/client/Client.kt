package io.silv.ktorsandwich.client

import io.ktor.client.HttpClient
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
    )
}

suspend inline fun <reified T> KSandwichClient.get(
    urlString: String,
    crossinline block: HttpRequestBuilder.() -> Unit = {}
): ApiResponse<T> {
    return ApiResponse.of { client.get(urlString, block) }
}

suspend inline fun <reified T> KSandwichClient.post(
    urlString: String,
    crossinline block: HttpRequestBuilder.() -> Unit = {}
): ApiResponse<T> {
    return ApiResponse.of { client.post(urlString, block) }
}

suspend inline fun <reified T> KSandwichClient.put(
    urlString: String,
    crossinline block: HttpRequestBuilder.() -> Unit = {}
): ApiResponse<T> {
    return ApiResponse.of { client.put(urlString, block) }
}

suspend inline fun <reified T> KSandwichClient.patch(
    urlString: String,
    crossinline block: HttpRequestBuilder.() -> Unit = {}
): ApiResponse<T> {
    return ApiResponse.of { client.patch(urlString, block) }
}




