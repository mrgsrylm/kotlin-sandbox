package io.github.mrgsrylm.composeshop.domain.use_cases.local

import io.github.mrgsrylm.composeshop.common.Resource
import io.github.mrgsrylm.composeshop.data.mappers.toUser
import io.github.mrgsrylm.composeshop.domain.model.user.User
import io.github.mrgsrylm.composeshop.domain.repository.BackendRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetSavedUserUseCase(
    private val repository: BackendRepository
) {
    suspend operator fun invoke(): Flow<Resource<User>> = flow {
        emit(Resource.Loading())
        try {
            if (repository.getSavedUser().isNotEmpty()) {
                emit(Resource.Success(repository.getSavedUser()[0].toUser()))
            } else {
                emit(Resource.Error(""))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }
}