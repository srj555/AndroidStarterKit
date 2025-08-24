package com.acme.feature.example.di

import com.acme.core.config.ConfigProvider
import com.acme.core.database.AppDatabase
import com.acme.core.database.DriverFactory
import com.acme.core.networking.HttpClientFactory
import com.acme.feature.example.data.local.ProductDao
import com.acme.feature.example.data.remote.ApiService
import com.acme.feature.example.data.repo.DefaultProductRepository
import com.acme.feature.example.domain.repo.ProductRepository
import com.acme.feature.example.domain.usecase.GetProductsUseCase
import com.acme.feature.example.domain.usecase.SearchProductsUseCase
import io.insert-koin.core.module.Module
import io.insert-koin.dsl.module

val dataModule: Module = module {
    single { (driverFactory: DriverFactory) ->
        AppDatabase(get<DriverFactory>().createDriver())
    }
    single { ProductDao(get()) }
    single { ApiService(HttpClientFactory.create(get<ConfigProvider>().config)) }
    single<ProductRepository> { DefaultProductRepository(get(), get(), get()) }
}

val domainModule: Module = module {
    factory { GetProductsUseCase(get()) }
    factory { SearchProductsUseCase(get()) }
}

val featureExampleModule: Module = module {
    includes(dataModule, domainModule)
}