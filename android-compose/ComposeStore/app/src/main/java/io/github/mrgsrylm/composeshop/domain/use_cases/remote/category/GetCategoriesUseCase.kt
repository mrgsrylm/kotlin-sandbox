package io.github.mrgsrylm.composeshop.domain.use_cases.remote.category

import io.github.mrgsrylm.composeshop.common.Resource
import io.github.mrgsrylm.composeshop.domain.model.category.ShopCategory
import io.github.mrgsrylm.composeshop.domain.repository.BackendRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.json.JSONObject
import java.util.Locale

class GetCategoriesUseCase(
    private val repository: BackendRepository
) {
    suspend operator fun invoke(token: String): Flow<Resource<List<ShopCategory>>> = flow {
        emit(Resource.Loading())
        try {
            val response = repository.getCategories(token)
            if (response.isSuccessful) {
                response.body()?.let { categories ->
                    val newCategories = mutableListOf<ShopCategory>()
                    categories.forEach { category ->
                        val newCategory = category.replace("-", " ").split(" ")
                            .joinToString(" ") { word ->
                                word.replaceFirstChar {
                                    if (it.isLowerCase()) it.titlecase(
                                        Locale.getDefault()
                                    ) else it.toString()
                                }
                            }
                        newCategories.add(ShopCategory(categoryId = category, name = newCategory))
                    }
                    emit(Resource.Success(newCategories))
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