package com.acme.feature.example.data.mapper

import com.acme.feature.example.data.dto.ProductDto
import com.acme.feature.example.domain.model.Product

object ProductMapper {
    fun toDomain(dto: ProductDto): Product = Product(
        id = dto.id.toString(),
        name = dto.title ?: dto.name ?: "Unnamed",
        price = dto.price,
        updatedAtMillis = kotlin.system.getTimeMillis()
    )
}