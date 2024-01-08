package io.github.mrgsrylm.composeshop.domain.use_cases.local

import io.github.mrgsrylm.composeshop.common.Resource
import io.github.mrgsrylm.composeshop.data.mappers.toProductEntity
import io.github.mrgsrylm.composeshop.domain.model.product.Product
import io.github.mrgsrylm.composeshop.domain.repository.BackendRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SaveToFavoritesUseCase(
    private val repository: BackendRepository
) {
    suspend operator fun invoke(product: Product): Flow<Resource<Product>> = flow {
        emit(Resource.Loading())
        try {
            repository.saveProductToFavorites(product.toProductEntity())
            emit(Resource.Success(product))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }
}