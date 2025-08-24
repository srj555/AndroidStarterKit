package com.acme.feature.example.domain.repo

import com.acme.core.common.Either
import com.acme.core.common.ResultError
import com.acme.feature.example.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun observeProducts(forceRefresh: Boolean = false): Flow<Either<ResultError, List<Product>>>
    suspend fun refresh(): Either<ResultError, List<Product>>
    suspend fun search(query: String): Either<ResultError, List<Product>>
}