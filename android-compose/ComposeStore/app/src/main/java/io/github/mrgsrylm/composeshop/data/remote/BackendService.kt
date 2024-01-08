package io.github.mrgsrylm.composeshop.data.remote

import io.github.mrgsrylm.composeshop.data.remote.dto.category.CategoryDTO
import io.github.mrgsrylm.composeshop.data.remote.dto.user.UserDTO
import io.github.mrgsrylm.composeshop.data.remote.dto.user.request.LoginRequest
import retrofit2.Response
import retrofit2.http.*

interface BackendService {
    @POST("auth/login")
    suspend fun login(@Body body: LoginRequest): Response<UserDTO>

    @GET("products/categories")
    suspend fun getCategories(@Header("Authorization") token: String): Response<List<String>>

    @GET("products/category/{category}")
    suspend fun getCategory(
        @Header("Authorization") token: String,
        @Path("category") categoryId: String
    ): Response<CategoryDTO>

    @GET("products")
    suspend fun getProducts(
        @Header("Authorization") token: String,
        @Query("limit") limit: Int = 10,
        @Query("skip") skip: Int = 0
    ): Response<CategoryDTO>

    companion object {
        const val API_URL = "https://dummyjson.com/"
    }
}