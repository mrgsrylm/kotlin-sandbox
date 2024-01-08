package io.github.mrgsrylm.composeshop.features.product.core

import io.github.mrgsrylm.composeshop.domain.model.product.Product

data class NewProductsState(
    val loading: Boolean = false,
    val newProducts: List<Product>? = null,
    val errorMsg: String? = null
) : ProductBaseState(loading, newProducts, errorMsg)