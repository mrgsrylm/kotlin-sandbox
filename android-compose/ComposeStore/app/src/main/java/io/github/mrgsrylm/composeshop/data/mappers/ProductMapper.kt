package io.github.mrgsrylm.composeshop.data.mappers

import io.github.mrgsrylm.composeshop.data.db.entities.ProductEntity
import io.github.mrgsrylm.composeshop.data.remote.dto.product.ProductDTO
import io.github.mrgsrylm.composeshop.domain.model.product.Product

fun ProductDTO.toProduct(): Product =
    Product(
        productId = id,
        image = thumbnail,
        title = title,
        subtitle = brand,
        price = price,
        discount = discountPercentage,
        rating = rating,
        description = description,
        images = images,
        category = category
    )

fun Product.toProductEntity(): ProductEntity =
    ProductEntity(
        productId = productId,
        image = image,
        title = title,
        subtitle = subtitle,
        price = price,
        discount = discount,
        rating = rating,
        description = description,
        images = images,
        category = category
    )

fun ProductEntity.toProduct(): Product =
    Product(
        productId,
        image,
        title,
        subtitle,
        price,
        discount,
        rating,
        description,
        images,
        category
    )