package com.acme.core.config

enum class Environment { DEV, STAGE, PROD }

data class AppConfig(
    val environment: Environment,
    val baseUrl: String,
    val enableLogging: Boolean,
)

interface ConfigProvider {
    val config: AppConfig
}