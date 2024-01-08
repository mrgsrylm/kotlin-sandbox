package io.github.mrgsrylm.composeshop.domain.model.category

import io.github.mrgsrylm.composeshop.domain.model.product.Product

data class Category(
    val categoryId: String,
    val name: String,
    val limit: Int,
    val products: List<Product>,
    val skip: Int,
    val total: Int
)
