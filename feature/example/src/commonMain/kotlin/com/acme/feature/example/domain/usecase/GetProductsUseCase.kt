package com.acme.feature.example.domain.usecase

import com.acme.core.common.Either
import com.acme.core.common.ResultError
import com.acme.feature.example.domain.model.Product
import com.acme.feature.example.domain.repo.ProductRepository
import kotlinx.coroutines.flow.Flow

class GetProductsUseCase(
    private val repository: ProductRepository
) {
    operator fun invoke(forceRefresh: Boolean = false): Flow<Either<ResultError, List<Product>>> =
        repository.observeProducts(forceRefresh)
}