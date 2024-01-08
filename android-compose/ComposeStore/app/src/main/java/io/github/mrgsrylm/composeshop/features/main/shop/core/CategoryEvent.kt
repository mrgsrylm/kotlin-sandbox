package io.github.mrgsrylm.composeshop.features.main.shop.core

sealed class CategoryEvent() {
    data class GetCategory(
        val token: String,
        val categoryId: String,
        val name: String
    ) : CategoryEvent()

    object CloseCategory : CategoryEvent()
}