package io.github.mrgsrylm.composeshop.features.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import io.github.mrgsrylm.composeshop.features.main.bag.Bag
import io.github.mrgsrylm.composeshop.features.main.favorites.Favorites
import io.github.mrgsrylm.composeshop.features.main.home.MainPage
import io.github.mrgsrylm.composeshop.features.main.home.utils.MainScreen
import io.github.mrgsrylm.composeshop.features.main.home.utils.navigationIcons
import io.github.mrgsrylm.composeshop.features.main.profile.Profile
import io.github.mrgsrylm.composeshop.features.main.shop.Shop
import io.github.mrgsrylm.composeshop.features.main.shop.core.CategoryEvent
import io.github.mrgsrylm.composeshop.ui.components.screen.Loading
import io.github.mrgsrylm.composeshop.ui.theme.ComposeShopTheme
import io.github.mrgsrylm.composeshop.ui.theme.Gray

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeShopTheme {
                val viewModel: MainViewModel = hiltViewModel()
                val screenLoading = viewModel.screenLoadingState
                val context = LocalContext.current
                val navController = rememberNavController()
                var currentDestination by remember {
                    mutableStateOf(
                        navController.currentDestination?.route ?: MainScreen.MainPage.route
                    )
                }
                if (!screenLoading) {
                    BackHandler {
                        if (viewModel.categoryState.isCategoryVisible) {
                            viewModel.onCategoriesEvent(CategoryEvent.CloseCategory)
                        } else {
                            if (navController.currentDestination?.route == MainScreen.MainPage.route) {
                                (context as ComponentActivity).finish()
                            } else {
                                navController.popBackStack()
                                navController.currentDestination?.route?.let {
                                    currentDestination = it
                                }
                            }
                        }
                    }
                }
                LaunchedEffect(key1 = currentDestination) {
                    if (!screenLoading) {
                        navController.navigate(currentDestination)
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = MaterialTheme.colorScheme.background),
                ) {
                    if (screenLoading) {
                        Loading(modifier = Modifier.fillMaxSize())
                    } else {
                        NavHost(
                            navController = navController,
                            startDestination = MainScreen.MainPage.route,
                            modifier = Modifier
                                .fillMaxSize()
                                .align(Alignment.Center)
                        ) {
                            composable(route = MainScreen.MainPage.route) {
                                MainPage(viewModel = viewModel)
                            }
                            composable(route = MainScreen.ShopPage.route) {
                                Shop(viewModel = viewModel)
                            }
                            composable(route = MainScreen.BagPage.route) {
                                Bag(viewModel = viewModel)
                            }
                            composable(route = MainScreen.FavoritesPage.route) {
                                Favorites(viewModel = viewModel)
                            }
                            composable(route = MainScreen.ProfilePage.route) {
                                Profile(viewModel = viewModel)
                            }
                        }
                        Card(
                            shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(0.1f)
                                .align(Alignment.BottomCenter),
                            elevation = 5.dp
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(color = MaterialTheme.colorScheme.secondary),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                navigationIcons.forEach { navIcon ->
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        IconButton(
                                            onClick = {
                                                currentDestination = navIcon.destination
                                            }
                                        ) {
                                            Icon(
                                                painterResource(
                                                    id =
                                                    if (navIcon.destination == currentDestination)
                                                        if (isSystemInDarkTheme())
                                                            navIcon.selectedIconDark
                                                        else
                                                            navIcon.selectedIcon
                                                    else
                                                        if (isSystemInDarkTheme())
                                                            navIcon.unselectedIconDark
                                                        else
                                                            navIcon.unselectedIcon
                                                ),
                                                null,
                                                modifier = Modifier.size(30.dp),
                                                tint = Color.Unspecified
                                            )
                                        }
                                        Text(
                                            text = navIcon.label,
                                            style = MaterialTheme.typography.headlineSmall,
                                            color = if (navIcon.destination == currentDestination) MaterialTheme.colorScheme.primary else Gray
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}