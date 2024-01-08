package io.github.mrgsrylm.composeshop.features.login.core

import io.github.mrgsrylm.composeshop.domain.model.user.User

data class LoginState(
    val isLoading: Boolean = false,
    val loggedUser: User? = null,
    val savedUser: User? = null,
    val error: String? = null
)