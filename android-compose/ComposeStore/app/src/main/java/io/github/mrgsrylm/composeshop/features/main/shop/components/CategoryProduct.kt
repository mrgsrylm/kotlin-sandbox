package io.github.mrgsrylm.composeshop.features.main.shop.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.mrgsrylm.composeshop.domain.model.product.Product
import io.github.mrgsrylm.composeshop.features.main.MainViewModel
import io.github.mrgsrylm.composeshop.ui.components.product.CardImage
import io.github.mrgsrylm.composeshop.ui.components.product.CircularButton
import io.github.mrgsrylm.composeshop.ui.components.product.ProductPrice
import io.github.mrgsrylm.composeshop.ui.components.product.ProductRating
import io.github.mrgsrylm.composeshop.ui.components.product.ProductSubtitle
import io.github.mrgsrylm.composeshop.ui.components.product.ProductTitle

@Composable
fun CategoryProduct(
    viewModel: MainViewModel,
    product: Product,
    onProductClick: (Product) -> Unit,
    onFavoriteClick: (Product) -> Unit,
    onDeleteFavoriteClick: (Product) -> Unit
) {
    val found = viewModel.favoritesState.favorites?.find { indexedProduct ->
        indexedProduct.productId == product.productId && indexedProduct.category == product.category
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clickable { onProductClick(product) },
            shape = RoundedCornerShape(20.dp),
            elevation = 5.dp
        ) {
            Row(
                modifier = Modifier.background(color = MaterialTheme.colorScheme.secondary),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CardImage(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize(), product.image
                )
                Column(
                    modifier = Modifier
                        .weight(2f)
                        .fillMaxSize()
                        .padding(10.dp),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    ProductTitle(product = product)
                    ProductSubtitle(product = product)
                    ProductRating(product = product)
                    ProductPrice(product = product)
                }
            }
        }
        CircularButton(
            modifier = Modifier
                .height(40.dp)
                .width(40.dp)
                .align(Alignment.BottomEnd)
                .offset(x = 0.dp, y = 5.dp),
            iconModifier = Modifier
                .width(25.dp)
                .height(25.dp),
            icon = if (found != null) Icons.Outlined.Favorite else Icons.Outlined.FavoriteBorder,
            tint = if (found != null) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSecondary,
            onClick = {
                if (found != null) {
                    onDeleteFavoriteClick(product)
                } else {
                    onFavoriteClick(product)
                }
            },
            iconDrawable = null
        )
    }
}