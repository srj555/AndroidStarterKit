package com.acme.legacy.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acme.core.common.Either
import com.acme.core.common.ResultError
import com.acme.feature.example.domain.model.Product
import com.acme.feature.example.domain.usecase.GetProductsUseCase
import com.acme.feature.example.domain.usecase.SearchProductsUseCase
import io.insert-koin.androidx.viewmodel.dsl.viewModel
import io.insert-koin.dsl.module
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

sealed interface UiState {
    data object Loading : UiState
    data class Data(val products: List<Product>) : UiState
    data class Error(val error: ResultError) : UiState
}

class ProductListViewModel(
    private val getProducts: GetProductsUseCase,
    private val searchProducts: SearchProductsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<UiState>(UiState.Loading)
    val state: StateFlow<UiState> = _state

    init { refresh() }

    fun refresh() {
        _state.value = UiState.Loading
        viewModelScope.launch {
            getProducts(true).collectLatest { result ->
                when (result) {
                    is Either.Left -> _state.value = UiState.Error(result.value)
                    is Either.Right -> _state.value = UiState.Data(result.value)
                }
            }
        }
    }

    fun search(query: String) {
        viewModelScope.launch {
            when (val r = searchProducts(query)) {
                is Either.Left -> _state.value = UiState.Error(r.value)
                is Either.Right -> _state.value = UiState.Data(r.value)
            }
        }
    }
}

val legacyViewModelModule = module {
    viewModel { ProductListViewModel(get(), get()) }
}