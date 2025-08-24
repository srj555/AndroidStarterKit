package com.acme.feature.example.data.local

import com.acme.core.database.AppDatabase
import com.acme.core.database.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductDao(private val database: AppDatabase) {
    suspend fun getAll(): List<Product> = withContext(Dispatchers.Default) {
        database.productQueries.selectAll().executeAsList()
    }

    suspend fun upsertAll(items: List<Product>) = withContext(Dispatchers.Default) {
        database.transaction {
            items.forEach { p ->
                database.productQueries.upsert(p.id, p.name, p.price, p.updatedAt)
            }
        }
    }

    suspend fun clear() = withContext(Dispatchers.Default) {
        database.productQueries.deleteAll()
    }
}