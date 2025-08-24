package com.acme.core.networking

import com.acme.core.config.AppConfig
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object HttpClientFactory {
    fun create(config: AppConfig, engine: HttpClient? = null): HttpClient {
        val json = Json {
            prettyPrint = false
            ignoreUnknownKeys = true
            explicitNulls = false
            isLenient = true
        }
        return HttpClient {
            install(ContentNegotiation) { json(json) }
            if (config.enableLogging) install(Logging) { level = LogLevel.INFO }
            defaultRequest { url(config.baseUrl) }
        }
    }
}