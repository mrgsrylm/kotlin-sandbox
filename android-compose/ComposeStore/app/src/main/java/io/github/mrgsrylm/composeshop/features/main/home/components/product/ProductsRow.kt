package io.github.mrgsrylm.composeshop.features.main.home.components.product

import android.app.Activity
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import io.github.mrgsrylm.composeshop.domain.model.product.Product
import io.github.mrgsrylm.composeshop.features.main.MainViewModel
import io.github.mrgsrylm.composeshop.features.main.home.utils.ProductType
import io.github.mrgsrylm.composeshop.features.product.AddToCartActivity
import io.github.mrgsrylm.composeshop.features.product.common.Constants
import io.github.mrgsrylm.composeshop.features.product.core.ProductBaseState
import io.github.mrgsrylm.composeshop.features.product.core.ProductEvent
import io.github.mrgsrylm.composeshop.ui.components.screen.Loading

@Composable
fun ProductsRow(
    productType: ProductType,
    viewModel: MainViewModel
) {
    val context = LocalContext.current
    var chosenProduct: Product? = null
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
    ) { activityResult ->
        if (activityResult.resultCode == Activity.RESULT_OK) {
            val result = activityResult.data?.getBooleanExtra(Constants.ADD_TO_CART, false)
            if (result == true) {
                chosenProduct?.let { product ->
                    viewModel.addProduct(product)
                }
            }
            viewModel.onProductEvent(ProductEvent.GetFavorites)
        }
    }

    val currentState: ProductBaseState = when (productType) {
        is ProductType.New -> viewModel.newProductsState
        is ProductType.Sale -> viewModel.saleProductsState
    }

    if (currentState.isLoading) {
        Loading(modifier = Modifier.fillMaxSize())
    } else {
        currentState.products?.let { products ->
            LazyRow(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(products) { product ->
                    ProductCard(
                        viewModel = viewModel,
                        product = product,
                        onProductClick = { chosenProd ->
                            chosenProduct = chosenProd
                            val intent = Intent(context, AddToCartActivity::class.java).apply {
                                putExtra(Constants.PRODUCT, chosenProduct)
                            }
                            launcher.launch(intent)
                        })
                }
            }
        }
    }
}