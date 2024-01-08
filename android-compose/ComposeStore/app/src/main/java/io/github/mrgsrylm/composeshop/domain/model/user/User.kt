package io.github.mrgsrylm.composeshop.domain.model.user

import java.io.Serializable

data class User(
    val username: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val image: String,
    val token: String
) : Serializable