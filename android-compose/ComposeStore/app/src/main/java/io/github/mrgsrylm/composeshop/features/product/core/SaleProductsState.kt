package io.github.mrgsrylm.composeshop.features.product.core

import io.github.mrgsrylm.composeshop.domain.model.product.Product

data class SaleProductsState(
    val loading: Boolean = false,
    val saleProducts: List<Product>? = null,
    val errorMsg: String? = null
) : ProductBaseState(loading, saleProducts, errorMsg)