package io.github.mrgsrylm.composeshop.data.mappers

import io.github.mrgsrylm.composeshop.data.remote.dto.category.CategoryDTO
import io.github.mrgsrylm.composeshop.domain.model.category.Category

fun CategoryDTO.toCategory(): Category =
    Category(
        "",
        "",
        limit,
        products.map { it.toProduct() },
        skip,
        total
    )