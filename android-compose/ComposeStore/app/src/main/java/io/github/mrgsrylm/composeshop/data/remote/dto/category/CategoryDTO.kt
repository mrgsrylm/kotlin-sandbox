package io.github.mrgsrylm.composeshop.data.remote.dto.category

import com.squareup.moshi.Json
import io.github.mrgsrylm.composeshop.data.remote.dto.product.ProductDTO

data class CategoryDTO(
    @Json(name = "limit")
    val limit: Int,
    @Json(name = "products")
    val products: List<ProductDTO>,
    @Json(name = "skip")
    val skip: Int,
    @Json(name = "total")
    val total: Int
)