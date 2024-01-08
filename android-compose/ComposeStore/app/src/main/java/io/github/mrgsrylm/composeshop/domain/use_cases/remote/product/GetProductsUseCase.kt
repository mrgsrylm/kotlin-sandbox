package io.github.mrgsrylm.composeshop.domain.use_cases.remote.product

import io.github.mrgsrylm.composeshop.common.Resource
import io.github.mrgsrylm.composeshop.data.mappers.toProduct
import io.github.mrgsrylm.composeshop.domain.model.product.Product
import io.github.mrgsrylm.composeshop.domain.repository.BackendRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.json.JSONObject

class GetProductsUseCase(
    private val repository: BackendRepository
) {
    suspend operator fun invoke(token: String, skip: Int = 0): Flow<Resource<List<Product>>> =
        flow {
            emit(Resource.Loading())
            try {
                val response = repository.getProducts(token, skip)
                if (response.isSuccessful) {
                    response.body()?.let { categoryDto ->
                        val productsMapped =
                            categoryDto.products.map { productDto -> productDto.toProduct() }
                        emit(Resource.Success(productsMapped))
                    }
                } else {
                    response.errorBody()?.let {
                        val jsonObj = JSONObject(it.string())
                        val errorMsg = jsonObj.getString("message")
                        emit(Resource.Error(errorMsg))
                    } ?: emit(Resource.Error("Something went wrong!"))
                }
            } catch (e: Exception) {
                emit(Resource.Error(e.message.toString()))
            }
        }
}