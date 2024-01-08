package io.github.mrgsrylm.composeshop.features.main.home.utils

sealed class ProductType {
    object New : ProductType()
    object Sale : ProductType()
}
