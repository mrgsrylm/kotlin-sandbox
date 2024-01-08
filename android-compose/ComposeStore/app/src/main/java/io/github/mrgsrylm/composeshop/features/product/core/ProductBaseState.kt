package io.github.mrgsrylm.composeshop.features.product.core

import io.github.mrgsrylm.composeshop.domain.model.product.Product

open class ProductBaseState(
    val isLoading: Boolean = false,
    val products: List<Product>? = null,
    val error: String? = null
)