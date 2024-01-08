package io.github.mrgsrylm.composeshop.ui.components.product

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import io.github.mrgsrylm.composeshop.domain.model.product.Product
import io.github.mrgsrylm.composeshop.ui.theme.Gray
import io.github.mrgsrylm.composeshop.ui.theme.Hot

@Composable
fun ProductPrice(
    product: Product,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        val strikethroughTextStyle = MaterialTheme.typography.bodyMedium.copy(
            textDecoration = TextDecoration.LineThrough
        )
        Text(
            text = "${product.price.toInt()}$",
            style = strikethroughTextStyle,
            color = Gray
        )
        Spacer(modifier = Modifier.width(2.dp))
        Text(
            text = "${(product.price - product.discount * 0.01 * product.price).toInt()}$",
            style = MaterialTheme.typography.bodyMedium,
            color = Hot
        )
    }
}