package io.github.mrgsrylm.composeshop.features.login.utils

sealed class LoginFieldType {
    object Email : LoginFieldType()
    object Password : LoginFieldType()
}