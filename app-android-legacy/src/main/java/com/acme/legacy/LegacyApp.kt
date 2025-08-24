package com.acme.legacy

import android.app.Application
import com.acme.core.config.AppConfig
import com.acme.core.config.ConfigProvider
import com.acme.core.config.Environment
import com.acme.core.database.DriverFactory
import com.acme.feature.example.di.featureExampleModule
import io.insert-koin.android.ext.koin.androidContext
import io.insert-koin.core.context.startKoin
import io.insert-koin.dsl.module

class LegacyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        val appModule = module {
            single<ConfigProvider> {
                val env = when (BuildConfig.FLAVOR) {
                    "dev" -> Environment.DEV
                    "stage" -> Environment.STAGE
                    else -> Environment.PROD
                }
                object : ConfigProvider {
                    override val config: AppConfig = AppConfig(
                        environment = env,
                        baseUrl = BuildConfig.BASE_URL,
                        enableLogging = BuildConfig.ENABLE_LOGGING
                    )
                }
            }
            single { DriverFactory(androidContext()) }
        }
        startKoin {
            androidContext(this@LegacyApp)
            modules(appModule, featureExampleModule)
        }
    }
}