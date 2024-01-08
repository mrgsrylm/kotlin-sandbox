package io.github.mrgsrylm.composeshop.features.main.home.utils

sealed class MainScreen(val route: String) {
    object MainPage : MainScreen("main_page")
    object ShopPage : MainScreen("shop_page")
    object BagPage : MainScreen("bag_page")
    object FavoritesPage : MainScreen("favorites_page")
    object ProfilePage : MainScreen("profile_page")

    fun withArgs(vararg args: String): String = buildString {
        append(route)
        args.forEach { arg ->
            append("/$arg")
        }
    }
}