package io.github.mrgsrylm.composeshop.data.repository

import io.github.mrgsrylm.composeshop.data.db.ShopDao
import io.github.mrgsrylm.composeshop.data.db.entities.ProductEntity
import io.github.mrgsrylm.composeshop.data.db.entities.UserEntity
import io.github.mrgsrylm.composeshop.data.remote.BackendService
import io.github.mrgsrylm.composeshop.data.remote.dto.category.CategoryDTO
import io.github.mrgsrylm.composeshop.data.remote.dto.user.UserDTO
import io.github.mrgsrylm.composeshop.data.remote.dto.user.request.LoginRequest
import io.github.mrgsrylm.composeshop.domain.repository.BackendRepository
import retrofit2.Response

class BackendRepositoryImpl(
    private val api: BackendService,
    private val dao: ShopDao
) : BackendRepository {
    override suspend fun login(username: String, password: String): Response<UserDTO> {
        return api.login(LoginRequest(username, password))
    }

    override suspend fun getSavedUser(): List<UserEntity> = dao.getUser()

    override suspend fun saveUser(user: UserEntity): Long = dao.saveUser(user)

    override suspend fun getCategories(token: String): Response<List<String>> {
        return api.getCategories(token)
    }

    override suspend fun getCategory(token: String, categoryId: String): Response<CategoryDTO> {
        return api.getCategory(token, categoryId)
    }

    override suspend fun saveProductToFavorites(product: ProductEntity): Long {
        return dao.saveProductToFavorites(product)
    }

    override suspend fun getFavorites(): List<ProductEntity> = dao.getFavorites()

    override suspend fun deleteFromFavorites(productId: Int, category: String) {
        dao.deleteFromFavorites(productId, category)
    }

    override suspend fun getProducts(token: String, skip: Int): Response<CategoryDTO> {
        return api.getProducts(token = token, skip = skip)
    }
}

