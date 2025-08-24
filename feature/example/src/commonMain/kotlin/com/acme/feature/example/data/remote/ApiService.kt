package com.acme.feature.example.data.remote

import com.acme.feature.example.data.dto.ProductDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ApiService(private val httpClient: HttpClient) {
    // Uses baseUrl provided in HttpClientFactory defaultRequest
    suspend fun getProducts(): List<ProductDto> = httpClient.get("/products").body()
}