package io.github.mrgsrylm.composeshop.domain.use_cases.remote.login

import io.github.mrgsrylm.composeshop.common.Resource
import io.github.mrgsrylm.composeshop.data.mappers.toUser
import io.github.mrgsrylm.composeshop.domain.model.user.User
import io.github.mrgsrylm.composeshop.domain.repository.BackendRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.json.JSONObject

class LoginUseCase(
    private val repository: BackendRepository
) {
    suspend operator fun invoke(username: String, password: String): Flow<Resource<User>> = flow {
        emit(Resource.Loading())
        try {
            val response = repository.login(username, password)
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(Resource.Success(it.toUser()))
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