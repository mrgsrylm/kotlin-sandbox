package io.github.mrgsrylm.composeshop.features.main.home.components.main_area

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.mrgsrylm.composeshop.features.main.MainViewModel
import io.github.mrgsrylm.composeshop.features.main.home.components.product.ProductsRow
import io.github.mrgsrylm.composeshop.features.main.home.utils.ProductType
import io.github.mrgsrylm.composeshop.ui.theme.Gray

@Composable
fun MainArea(
    productType: ProductType,
    mainAreaLabel: String,
    mainAreaSubtitle: String,
    viewModel: MainViewModel
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column {
                Text(
                    text = mainAreaLabel,
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = mainAreaSubtitle,
                    style = MaterialTheme.typography.headlineLarge,
                    color = Gray
                )
            }
            Text(
                text = "View all",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
        ProductsRow(productType = productType, viewModel = viewModel)
    }
}