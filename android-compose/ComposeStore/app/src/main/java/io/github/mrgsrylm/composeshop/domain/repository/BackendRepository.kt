package io.github.mrgsrylm.composeshop.domain.repository

import io.github.mrgsrylm.composeshop.data.db.entities.ProductEntity
import io.github.mrgsrylm.composeshop.data.db.entities.UserEntity
import io.github.mrgsrylm.composeshop.data.remote.dto.category.CategoryDTO
import io.github.mrgsrylm.composeshop.data.remote.dto.user.UserDTO
import retrofit2.Response

interface BackendRepository {
    suspend fun login(username: String, password: String): Response<UserDTO>
    suspend fun getSavedUser(): List<UserEntity>
    suspend fun saveUser(user: UserEntity): Long
    suspend fun getCategories(token: String): Response<List<String>>
    suspend fun getCategory(token: String, categoryId: String): Response<CategoryDTO>
    suspend fun saveProductToFavorites(product: ProductEntity): Long
    suspend fun getFavorites(): List<ProductEntity>
    suspend fun deleteFromFavorites(productId: Int, category: String)
    suspend fun getProducts(token: String, skip: Int = 0): Response<CategoryDTO>
}