package io.github.mrgsrylm.composeshop.data.remote.dto.user.request

import com.squareup.moshi.Json

data class LoginRequest(
    @Json(name = "username") val username: String,
    @Json(name = "password") val password: String
)