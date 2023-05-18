package io.silv.ktorsandwich.client

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.silv.ktorsandwich.operators.SandwichOperator
import io.silv.ktorsandwich.KSandwichInitializer


interface KSandwichClient {

    val client: HttpClient

    fun engine(
        engineFactory: HttpClientEngineFactory<HttpClientEngineConfig>,
        engineConfig:  HttpClientEngineFactory<HttpClientEngineConfig>.() -> Unit,
    )


    fun addGlobalOperator(operator: SandwichOperator)

    companion object {

        fun create(block: KSandwichClient.() -> Unit): KSandwichClient {
            return KSandwichClientImpl().apply(block)
        }
    }
}

internal class KSandwichClientImpl: KSandwichClient {


    override val client = HttpClient(
        KSandwichInitializer.engineFactory.apply(
            KSandwichInitializer.engineConfig
        )
    )

    override fun engine(
        engineFactory: HttpClientEngineFactory<HttpClientEngineConfig>,
        engineConfig: HttpClientEngineFactory<HttpClientEngineConfig>.() -> Unit
    ) {
        KSandwichInitializer.engineFactory = engineFactory
        KSandwichInitializer.engineConfig = engineConfig
    }

    override fun addGlobalOperator(operator: SandwichOperator) {
        KSandwichInitializer.sandwichOperators += operator
    }
}





