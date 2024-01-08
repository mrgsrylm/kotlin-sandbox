package io.github.mrgsrylm.composeshop.ui.components.product

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import io.github.mrgsrylm.composeshop.domain.model.product.Product

@Composable
fun ProductTitle(
    product: Product
) {
    Text(
        text = product.title,
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onBackground,
        fontWeight = FontWeight.Bold
    )
}