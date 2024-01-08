package io.github.mrgsrylm.composeshop.data.mappers

import io.github.mrgsrylm.composeshop.data.db.entities.UserEntity
import io.github.mrgsrylm.composeshop.data.remote.dto.user.UserDTO
import io.github.mrgsrylm.composeshop.domain.model.user.User

fun User.toUserEntity(): UserEntity =
    UserEntity(
        username = username,
        email = email,
        firstName = firstName,
        lastName = lastName,
        image = image,
        token = token
    )

fun UserDTO.toUser(): User = User(username, email, firstName, lastName, image, token)

fun UserEntity.toUser(): User = User(username, email, firstName, lastName, image, token)