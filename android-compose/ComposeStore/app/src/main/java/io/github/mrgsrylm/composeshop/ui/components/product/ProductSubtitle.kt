package io.github.mrgsrylm.composeshop.ui.components.product

import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import io.github.mrgsrylm.composeshop.domain.model.product.Product
import io.github.mrgsrylm.composeshop.ui.theme.Gray

@Composable
fun ProductSubtitle(
    product: Product
) {
    Text(
        text = product.subtitle,
        style = MaterialTheme.typography.headlineSmall,
        color = Gray,
    )
}