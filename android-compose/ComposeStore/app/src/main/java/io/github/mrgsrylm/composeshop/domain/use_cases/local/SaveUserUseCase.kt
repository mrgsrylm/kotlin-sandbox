package io.github.mrgsrylm.composeshop.domain.use_cases.local

import io.github.mrgsrylm.composeshop.common.Resource
import io.github.mrgsrylm.composeshop.data.mappers.toUserEntity
import io.github.mrgsrylm.composeshop.domain.model.user.User
import io.github.mrgsrylm.composeshop.domain.repository.BackendRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SaveUserUseCase(
    private val repository: BackendRepository
) {
    suspend operator fun invoke(user: User): Flow<Resource<User>> = flow {
        emit(Resource.Loading())
        try {
            repository.saveUser(user.toUserEntity())
            emit(Resource.Success(user))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }
}