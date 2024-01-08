package io.github.mrgsrylm.composeshop.features.main.favorites.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.mrgsrylm.composeshop.domain.model.product.Product
import io.github.mrgsrylm.composeshop.ui.components.product.CardImage
import io.github.mrgsrylm.composeshop.ui.components.product.ColorAndSize
import io.github.mrgsrylm.composeshop.ui.components.product.ProductPrice
import io.github.mrgsrylm.composeshop.ui.components.product.ProductRating
import io.github.mrgsrylm.composeshop.ui.components.product.ProductSubtitle
import io.github.mrgsrylm.composeshop.ui.components.product.ProductTitle
import io.github.mrgsrylm.composeshop.ui.theme.Gray

@Composable
fun FavoriteProduct(
    favorite: Product,
    onRemoveClick: (Product) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
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
                    .fillMaxSize(), favorite.image
            )
            Column(
                modifier = Modifier
                    .weight(2f)
                    .fillMaxSize()
                    .padding(10.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ProductSubtitle(product = favorite)
                    IconButton(onClick = {
                        onRemoveClick(favorite)
                    }) {
                        Icon(
                            imageVector = Icons.Outlined.Close,
                            contentDescription = null,
                            tint = Gray
                        )
                    }
                }
                ProductTitle(product = favorite)
                ColorAndSize()
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ProductRating(product = favorite, modifier = Modifier.weight(1f))
                    ProductPrice(product = favorite, modifier = Modifier.weight(1f))
                }
            }
        }
    }
}