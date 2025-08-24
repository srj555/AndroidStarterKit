package com.acme.feature.example.data.repo

import com.acme.core.common.Either
import com.acme.core.common.ResultError
import com.acme.core.common.ResultError.Network
import com.acme.core.common.ResultError.Serialization
import com.acme.core.common.ResultError.Unknown
import com.acme.core.database.AppDatabase
import com.acme.feature.example.data.local.ProductDao
import com.acme.feature.example.data.mapper.ProductMapper
import com.acme.feature.example.data.remote.ApiService
import com.acme.feature.example.domain.model.Product
import com.acme.feature.example.domain.repo.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DefaultProductRepository(
    private val api: ApiService,
    private val dao: ProductDao,
    private val db: AppDatabase
) : ProductRepository {

    override fun observeProducts(forceRefresh: Boolean): Flow<Either<ResultError, List<Product>>> = flow {
        val cached = dao.getAll().map { p -> Product(p.id, p.name, p.price, p.updatedAt) }
        if (cached.isNotEmpty()) emit(Either.Right(cached)) else emit(Either.Right(emptyList()))
        if (forceRefresh || cached.isEmpty()) {
            emit(refresh())
        }
    }

    override suspend fun refresh(): Either<ResultError, List<Product>> = try {
        val dtos = api.getProducts()
        val domain = dtos.map(ProductMapper::toDomain)
        dao.upsertAll(domain.map { d -> com.acme.core.database.Product(d.id, d.name, d.price, d.updatedAtMillis) })
        Either.Right(domain)
    } catch (e: Throwable) {
        when (e) {
            is kotlinx.serialization.SerializationException -> Either.Left(Serialization(e.message, e))
            is io.ktor.client.plugins.ClientRequestException,
            is io.ktor.client.plugins.ServerResponseException,
            is io.ktor.network.sockets.SocketTimeoutException -> Either.Left(Network(e.message, e))
            else -> Either.Left(Unknown(e.message, e))
        }
    }

    override suspend fun search(query: String): Either<ResultError, List<Product>> {
        val all = dao.getAll().map { p -> Product(p.id, p.name, p.price, p.updatedAt) }
        val filtered = all.filter { it.name.contains(query, ignoreCase = true) }
        return Either.Right(filtered)
    }
}