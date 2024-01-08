package io.github.mrgsrylm.composeshop.domain.use_cases.remote.category

import io.github.mrgsrylm.composeshop.common.Resource
import io.github.mrgsrylm.composeshop.data.mappers.toCategory
import io.github.mrgsrylm.composeshop.domain.model.category.Category
import io.github.mrgsrylm.composeshop.domain.repository.BackendRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.json.JSONObject

class GetCategoryUseCase(
    private val repository: BackendRepository
) {
    suspend operator fun invoke(
        token: String,
        categoryId: String,
        name: String
    ): Flow<Resource<Category>> = flow {
        emit(Resource.Loading())
        try {
            val response = repository.getCategory(token, categoryId)
            if (response.isSuccessful) {
                response.body()?.let { categoryDto ->
                    val category = categoryDto.toCategory().copy(
                        categoryId = categoryId,
                        name = name
                    )
                    emit(Resource.Success(category))
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