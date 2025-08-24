package com.acme.feature.example.domain.model

data class Product(
    val id: String,
    val name: String,
    val price: Double,
    val updatedAtMillis: Long,
)