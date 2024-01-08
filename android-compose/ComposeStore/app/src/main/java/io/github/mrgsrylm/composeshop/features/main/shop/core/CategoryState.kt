package io.github.mrgsrylm.composeshop.features.main.shop.core

import io.github.mrgsrylm.composeshop.domain.model.category.Category

data class CategoryState(
    val isLoading: Boolean = false,
    val category: Category? = null,
    val error: String? = null,
    val isCategoryVisible: Boolean = false
)