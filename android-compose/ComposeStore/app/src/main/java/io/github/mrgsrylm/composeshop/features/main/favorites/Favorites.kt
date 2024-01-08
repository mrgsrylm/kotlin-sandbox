package io.github.mrgsrylm.composeshop.features.main.favorites

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.mrgsrylm.composeshop.common.FeedbackType
import io.github.mrgsrylm.composeshop.features.main.MainViewModel
import io.github.mrgsrylm.composeshop.features.main.favorites.components.FavoriteProduct
import io.github.mrgsrylm.composeshop.features.product.core.ProductEvent
import io.github.mrgsrylm.composeshop.ui.components.appbar.AppBar
import io.github.mrgsrylm.composeshop.ui.components.feedback.FeedbackLabel
import io.github.mrgsrylm.composeshop.ui.components.screen.BottomPaddingColumn
import io.github.mrgsrylm.composeshop.ui.components.screen.Loading
import io.github.mrgsrylm.composeshop.ui.components.screen.ScreenTitle

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Favorites(
    viewModel: MainViewModel
) {
    val favoritesState = viewModel.favoritesState

    Scaffold(modifier = Modifier
        .fillMaxSize(),
        topBar = {
            AppBar(
                isBackEnabled = false,
                actions = arrayOf(Icons.Outlined.Search)
            )
        },
        content = {
            BottomPaddingColumn {
                ScreenTitle(title = "Favorites")
                if (favoritesState.isLoading) {
                    Loading(modifier = Modifier.fillMaxSize())
                } else {
                    if (favoritesState.error != null && favoritesState.error.isNotBlank()) {
                        FeedbackLabel(
                            modifier = Modifier.fillMaxSize(),
                            FeedbackType.Error(favoritesState.error)
                        )
                    } else {
                        if (favoritesState.favorites != null) {
                            LazyColumn(
                                modifier = Modifier
                                    .fillMaxSize(),
                                contentPadding = PaddingValues(10.dp),
                                verticalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                items(favoritesState.favorites) { favorite ->
                                    FavoriteProduct(
                                        favorite = favorite,
                                        onRemoveClick = { chosenFavorite ->
                                            viewModel.onProductEvent(
                                                ProductEvent.DeleteFromFavorites(
                                                    chosenFavorite
                                                )
                                            )
                                        })
                                }
                            }
                        } else {
                            FeedbackLabel(
                                modifier = Modifier.fillMaxSize(),
                                FeedbackType.Info("There is no favorites")
                            )
                        }
                    }
                }
            }
        })
}