package io.github.mrgsrylm.composeshop.ui.components.product

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.mrgsrylm.composeshop.domain.model.product.Product
import io.github.mrgsrylm.composeshop.ui.theme.Gray
import io.github.mrgsrylm.composeshop.ui.theme.StartYellow

@Composable
fun ProductRating(
    product: Product,
    modifier: Modifier = Modifier
) {
    Row(modifier = Modifier, verticalAlignment = Alignment.CenterVertically) {
        repeat(5) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = null,
                modifier = Modifier
                    .height(15.dp)
                    .width(15.dp),
                tint = StartYellow
            )
        }
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = "(${product.rating})",
            style = MaterialTheme.typography.headlineSmall,
            color = Gray
        )
    }
}