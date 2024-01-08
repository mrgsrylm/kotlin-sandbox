package io.github.mrgsrylm.composeshop.features.main.favorites.core

import io.github.mrgsrylm.composeshop.domain.model.product.Product

data class FavoritesState(
    val isLoading: Boolean = false,
    val favorites: List<Product>? = null,
    val error: String? = null
)
