package com.acme.feature.example.domain.usecase

import com.acme.core.common.Either
import com.acme.core.common.ResultError
import com.acme.feature.example.domain.model.Product
import com.acme.feature.example.domain.repo.ProductRepository

class SearchProductsUseCase(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(query: String): Either<ResultError, List<Product>> =
        repository.search(query)
}