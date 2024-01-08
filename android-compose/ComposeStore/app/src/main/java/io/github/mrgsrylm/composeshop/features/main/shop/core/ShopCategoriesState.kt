package io.github.mrgsrylm.composeshop.features.main.shop.core

import io.github.mrgsrylm.composeshop.domain.model.category.ShopCategory

data class ShopCategoriesState(
    val isLoading: Boolean = false,
    val shopCategories: List<ShopCategory>? = null,
    val error: String? = null
)