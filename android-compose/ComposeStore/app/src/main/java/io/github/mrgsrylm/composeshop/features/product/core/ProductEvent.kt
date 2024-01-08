package io.github.mrgsrylm.composeshop.features.product.core

import io.github.mrgsrylm.composeshop.domain.model.product.Product

sealed class ProductEvent {
    data class SaveToFavorites(val product: Product) : ProductEvent()
    object GetFavorites : ProductEvent()
    data class DeleteFromFavorites(val product: Product) : ProductEvent()
}