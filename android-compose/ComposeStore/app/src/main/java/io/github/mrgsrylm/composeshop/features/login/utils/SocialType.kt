package io.github.mrgsrylm.composeshop.features.login.utils

sealed class SocialType {
    object Google : SocialType()
    object Facebook : SocialType()
}
