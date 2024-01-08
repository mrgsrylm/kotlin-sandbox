package io.github.mrgsrylm.composeshop.domain.use_cases.local

import io.github.mrgsrylm.composeshop.common.Resource
import io.github.mrgsrylm.composeshop.data.mappers.toProduct
import io.github.mrgsrylm.composeshop.domain.model.product.Product
import io.github.mrgsrylm.composeshop.domain.repository.BackendRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetFavoritesUseCase(
    private val repository: BackendRepository
) {
    suspend operator fun invoke(): Flow<Resource<List<Product>>> = flow {
        emit(Resource.Loading())
        try {
            val favoritesEntities = repository.getFavorites()
            if (favoritesEntities.isNotEmpty()) {
                val favorites = favoritesEntities.map { favorite -> favorite.toProduct() }
                emit(Resource.Success(favorites))
            } else {
                emit(Resource.Error(""))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }
}