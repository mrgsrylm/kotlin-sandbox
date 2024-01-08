package io.github.mrgsrylm.composeshop.features.login.core

import io.github.mrgsrylm.composeshop.domain.model.user.User

sealed class LoginEvent {
    data class Login(val username: String, val password: String) : LoginEvent()
    data class SaveUser(val user: User) : LoginEvent()
}